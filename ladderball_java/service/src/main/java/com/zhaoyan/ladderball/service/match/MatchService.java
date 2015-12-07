package com.zhaoyan.ladderball.service.match;

import com.zhaoyan.ladderball.dao.match.MatchDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.dao.recordermatch.RecorderMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TeamOfMatchDao;
import com.zhaoyan.ladderball.domain.match.db.Match;
import com.zhaoyan.ladderball.domain.match.http.MatchDetailRequest;
import com.zhaoyan.ladderball.domain.match.http.MatchDetailResponse;
import com.zhaoyan.ladderball.domain.match.http.MatchListRequest;
import com.zhaoyan.ladderball.domain.match.http.MatchListResponse;
import com.zhaoyan.ladderball.domain.player.db.PlayerOfMatch;
import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderMatch;
import com.zhaoyan.ladderball.domain.teamofmatch.db.TeamOfMatch;
import com.zhaoyan.ladderball.service.common.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService extends BaseService {
    Logger logger = LoggerFactory.getLogger(MatchService.class);

    @Autowired
    @Qualifier("hibernateMatchDao")
    MatchDao matchDao;

    @Autowired
    @Qualifier("hibernateRecorderMatchDao")
    RecorderMatchDao recorderMatchDao;

    @Autowired
    @Qualifier("hibernateTeamOfMatchDao")
    TeamOfMatchDao teamOfMatchDao;

    @Autowired
    @Qualifier("hibernatePlayerOfMatchDao")
    PlayerOfMatchDao playerOfMatchDao;

    private static BeanCopier copierMatchToMatchListResponse =
            BeanCopier.create(Match.class, MatchListResponse.Match.class, false);
    private static BeanCopier copierTeamToMatchListResponse =
            BeanCopier.create(TeamOfMatch.class, MatchListResponse.Team.class, false);
    private static BeanCopier copierTeamToMatchDetailResponse =
            BeanCopier.create(TeamOfMatch.class, MatchDetailResponse.Team.class, false);
    private static BeanCopier copierPlayerToMatchDetailResponse =
            BeanCopier.create(PlayerOfMatch.class, MatchDetailResponse.Player.class, false);

    /**
     * 获取当前用户的比赛
     */
    public MatchListResponse getMatchList(MatchListRequest request) {
        MatchListResponse response = new MatchListResponse();
        response.buildOk();
        response.matches = new ArrayList<>();

        long recorderId = Long.valueOf(request.header.userToken);
        List<RecorderMatch> recorderMatches = recorderMatchDao.getRecorderMatchByRecorder(recorderId);


        if (recorderMatches != null) {
            for (RecorderMatch recorderMatch : recorderMatches) {
                logger.debug("getMatchList() RecorderMatch: " + recorderMatch);
                Match match = matchDao.getMatch(recorderMatch.matchId);
                if (match != null) {
                    MatchListResponse.Match m = new MatchListResponse.Match();
                    copierMatchToMatchListResponse.copy(match, m, null);
                    m.startTime = match.startTime.getTime();
                    // 获取主队和客队信息
                    TeamOfMatch teamHome = teamOfMatchDao.getTeamOfMatch(match.teamHome);
                    if (teamHome != null) {
                        m.teamHome = new MatchListResponse.Team();
                        copierTeamToMatchListResponse.copy(teamHome, m.teamHome, null);
                        m.teamHome.isAsigned = recorderMatch.asignedTeam == RecorderMatch.ASIGNED_TEAM_HOME;
                    }

                    TeamOfMatch teamVisitor = teamOfMatchDao.getTeamOfMatch(match.teamVisitor);
                    if (teamVisitor != null) {
                        m.teamVisitor = new MatchListResponse.Team();
                        copierTeamToMatchListResponse.copy(teamVisitor, m.teamVisitor, null);
                        m.teamVisitor.isAsigned = recorderMatch.asignedTeam == RecorderMatch.ASIGNED_TEAM_VISITOR;
                    }

                    response.matches.add(m);
                }
            }
        }

        return response;
    }

    /**
     * 获取比赛详情
     */
    public MatchDetailResponse getMatchDetail(MatchDetailRequest request) {
        MatchDetailResponse response = new MatchDetailResponse();

        Match match = matchDao.getMatch(request.matchId);
        if (match != null) {
            response.buildOk();
            // 比赛数据
            response.id = match.id;
            response.partMinutes = match.partMinutes;
            response.totalPart = match.totalPart;
            response.playerNumber = match.playerNumber;
            response.address = match.address;
            response.startTime = match.startTime.getTime();
            // 队伍数据
            long recorderId = getRecorderIdFromUserToken(request.header.userToken);

            // 分配的队伍
            RecorderMatch recorderMatch = recorderMatchDao.getRecorderMatchByRecorderMatch(recorderId, request.matchId);

            // 获取主队和客队信息
            TeamOfMatch teamHome = teamOfMatchDao.getTeamOfMatch(match.teamHome);
            if (teamHome != null) {
                response.teamHome = new MatchDetailResponse.Team();
                copierTeamToMatchDetailResponse.copy(teamHome, response.teamHome, null);
                if (recorderMatch != null) {
                    response.teamHome.isAsigned = recorderMatch.asignedTeam == RecorderMatch.ASIGNED_TEAM_HOME;
                    if (response.teamHome.isAsigned) {
                        response.teamHome.players = getPlayers(response.teamHome.id);
                    }
                }
            }

            TeamOfMatch teamVisitor = teamOfMatchDao.getTeamOfMatch(match.teamVisitor);
            if (teamHome != null) {
                response.teamVisitor = new MatchDetailResponse.Team();
                copierTeamToMatchDetailResponse.copy(teamVisitor, response.teamVisitor, null);
                if (recorderMatch != null) {
                    response.teamVisitor.isAsigned = recorderMatch.asignedTeam == RecorderMatch.ASIGNED_TEAM_VISITOR;
                    if (response.teamVisitor.isAsigned) {
                        response.teamVisitor.players = getPlayers(response.teamVisitor.id);
                    }
                }
            }

            // 小节数据
            // TODO 暂时没有小节数据，先模拟
            response.partDatas = new ArrayList<>();
            // 小节号从1开始
            for(int i = 1; i<= response.totalPart; i++) {
                MatchDetailResponse.PartData partData = new MatchDetailResponse.PartData();
                partData.partNumber = i;
                partData.isComplete = false;

                response.partDatas.add(partData);
            }
        } else {
            response.buildFail("没有找到比赛");
        }

        return response;
    }

    public List<MatchDetailResponse.Player> getPlayers(long teamId) {
        List<MatchDetailResponse.Player> players = new ArrayList<>();

        List<PlayerOfMatch> playerOfMatches = playerOfMatchDao.getPlayerByTeam(teamId);
        if (playerOfMatches != null) {
            for (PlayerOfMatch p : playerOfMatches) {
                MatchDetailResponse.Player player = new MatchDetailResponse.Player();
                copierPlayerToMatchDetailResponse.copy(p, player, null);
                players.add(player);
            }
        } else {
            logger.debug("getMatchDetail() can not find player of team: " + teamId);
        }

        return players;
    }
}
