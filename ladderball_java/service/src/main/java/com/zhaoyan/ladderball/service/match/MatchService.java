package com.zhaoyan.ladderball.service.match;

import com.zhaoyan.ladderball.dao.match.MatchDao;
import com.zhaoyan.ladderball.dao.match.MatchPartDao;
import com.zhaoyan.ladderball.dao.player.PlayerOfMatchDao;
import com.zhaoyan.ladderball.dao.recordermatch.RecorderMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TeamOfMatchDao;
import com.zhaoyan.ladderball.domain.match.db.Match;
import com.zhaoyan.ladderball.domain.match.db.MatchPart;
import com.zhaoyan.ladderball.domain.match.http.*;
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
import java.util.Date;
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

    @Autowired
    @Qualifier("hibernateMatchPartDao")
    MatchPartDao matchPartDao;

    private static BeanCopier copierMatchToMatchListResponse =
            BeanCopier.create(Match.class, MatchListResponse.Match.class, false);
    private static BeanCopier copierTeamToMatchListResponse =
            BeanCopier.create(TeamOfMatch.class, MatchListResponse.Team.class, false);
    private static BeanCopier copierTeamToMatchDetailResponse =
            BeanCopier.create(TeamOfMatch.class, MatchDetailResponse.Team.class, false);
    private static BeanCopier copierPlayerToMatchDetailResponse =
            BeanCopier.create(PlayerOfMatch.class, MatchDetailResponse.Player.class, false);
    private static BeanCopier copierMatchModifyReqestToPlayer =
            BeanCopier.create(MatchModifyRequest.Player.class, PlayerOfMatch.class, false);

    // 添加球员
    private static BeanCopier copierMatchAddPlayerRequestToPlayer =
            BeanCopier.create(MatchAddPlayerRequest.Player.class, PlayerOfMatch.class, false);
    private static BeanCopier copierPlayerOfMatchToMatchAddPlayerResponse =
            BeanCopier.create(PlayerOfMatch.class, MatchAddPlayerResponse.Player.class, false);

    // 添加比赛
    private static BeanCopier copierMatchAddRequestToMatch =
            BeanCopier.create(MatchAddRequest.class, Match.class, false);


    /**
     * 获取当前用户的比赛
     */
    public MatchListResponse getMatchList(MatchListRequest request) {
        MatchListResponse response = new MatchListResponse();
        response.buildOk();
        response.matches = new ArrayList<>();

        long recorderId = Long.valueOf(request.header.userToken);

        List<RecorderMatch> recorderMatches = null;
        switch (request.completeType) {
            case MatchListRequest.TYPE_ALL:
                recorderMatches = recorderMatchDao.getRecorderMatchByRecorder(recorderId);
                break;
            case MatchListRequest.TYPE_COMPLETE:
                recorderMatches = recorderMatchDao.getRecorderMatchByRecorder(recorderId, true);
                break;
            case MatchListRequest.TYPE_NOT_COMPLETE:
                recorderMatches = recorderMatchDao.getRecorderMatchByRecorder(recorderId, false);
                break;
        }

        if (recorderMatches != null) {
            for (RecorderMatch recorderMatch : recorderMatches) {
                logger.debug("getMatchList() RecorderMatch: " + recorderMatch);
                Match match = recorderMatch.match;
                if (match != null) {
                    MatchListResponse.Match m = new MatchListResponse.Match();
                    copierMatchToMatchListResponse.copy(match, m, null);
                    if (match.startTime != null) {
                        m.startTime = match.startTime.getTime();
                    }
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
            if (match.startTime != null) {
                response.startTime = match.startTime.getTime();
            }
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
            response.partDatas = new ArrayList<>();
            List<MatchPart> matchParts = matchPartDao.getMatchParts(request.matchId);
            for (MatchPart part : matchParts) {

                MatchDetailResponse.PartData partData = new MatchDetailResponse.PartData();
                partData.partNumber = part.partNumber;
                partData.isComplete = part.isComplete;

                response.partDatas.add(partData);
            }
        } else {
            response.buildFail("没有找到比赛");
        }

        return response;
    }

    private List<MatchDetailResponse.Player> getPlayers(long teamId) {
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

    /**
     * 设置比赛
     */
    public MatchModifyResponse modifyMatch(MatchModifyRequest request) {
        MatchModifyResponse response = new MatchModifyResponse();
        response.buildOk();
        // 修改比赛表
        Match match = matchDao.getMatch(request.matchId);
        if (match == null) {
            logger.warn("modifyMatch() modify match fail. matchId: " + request.matchId);
            response.buildFail();
            return response;
        } else {
            match.playerNumber = request.playerNumber;
            match.totalPart = request.totalPart;
            match.partMinutes = request.partMinutes;
            matchDao.modifyMatch(match);
        }

        // 修改比赛小节数据
        List<MatchPart> matchParts = matchPartDao.getMatchParts(request.matchId);
        if (request.totalPart > matchParts.size()) {
            // 节数增加了
            for (int i = matchParts.size() + 1; i <= request.totalPart; i++) {
                MatchPart matchPart = new MatchPart();
                matchPart.matchId = request.matchId;
                matchPart.partNumber = i;
                matchPart.isComplete = false;
                matchPartDao.addMatchPart(matchPart);
            }
        } else if (request.totalPart < matchParts.size()) {
            // 节数减少了
            for (MatchPart matchPart : matchParts) {
                if (matchPart.partNumber > request.totalPart) {
                    matchPartDao.deleteMatchPart(matchPart);
                }
            }
        }

        // 修改球员数据
        for (MatchModifyRequest.Player player : request.players) {
            PlayerOfMatch newPlayer = playerOfMatchDao.getPlayerById(player.id);
            if (newPlayer != null) {
                copierMatchModifyReqestToPlayer.copy(player, newPlayer, null);
                playerOfMatchDao.modifyPlayer(newPlayer);
            } else {
                logger.warn("modifyMatch() error, player id not found. playerId: " + player.id);
            }
        }
        return response;
    }

    /**
     * 添加球员
     */
    public MatchAddPlayerResponse addPlayer(MatchAddPlayerRequest request) {
        MatchAddPlayerResponse response = new MatchAddPlayerResponse();

        PlayerOfMatch playerOfMatch = new PlayerOfMatch();
        copierMatchAddPlayerRequestToPlayer.copy(request.player, playerOfMatch, null);
        playerOfMatch.teamId = request.teamId;

        // 不能添加重复号码的球员
        boolean isRepeatedNumber = playerOfMatchDao.isPlayerNumberRepeated(playerOfMatch);
        if (isRepeatedNumber) {
            response.buildFail("不能添加重复号码的球员");
        } else {
            playerOfMatchDao.addPlayer(playerOfMatch);

            // 将结果添加到response
            MatchAddPlayerResponse.Player resultPlayer = new MatchAddPlayerResponse.Player();
            copierPlayerOfMatchToMatchAddPlayerResponse.copy(playerOfMatch, resultPlayer, null);
            response.buildOk();

            response.player = resultPlayer;
        }

        return response;
    }

    /**
     * 提交比赛
     */
    public MatchSubmitResponse submitMatch(MatchSubmitRequest request) {
        MatchSubmitResponse response = new MatchSubmitResponse();

        Match match = matchDao.getMatch(request.matchId);
        if (match == null) {
            response.buildFail("无法找到该场比赛");
        } else {
            match.complete = true;
            matchDao.modifyMatch(match);
            response.buildOk();
        }
        return response;
    }

    /**
     * 添加一场比赛
     */
    public MatchAddResponse addMatch(MatchAddRequest request) {
        // 添加主队
        TeamOfMatch teamHome = new TeamOfMatch();
        teamHome.name = request.teamHomeName;
        teamOfMatchDao.addTeamOfMatch(teamHome);

        // 添加客队
        TeamOfMatch teamVisitor = new TeamOfMatch();
        teamVisitor.name = request.teamVisitorName;
        teamOfMatchDao.addTeamOfMatch(teamVisitor);

        // 添加比赛
        Match match = new Match();
        copierMatchAddRequestToMatch.copy(request, match, null);
        match.teamHome = teamHome.id;
        match.teamVisitor = teamVisitor.id;
        match.startTime = new Date(request.startTime);
        matchDao.addMatch(match);

        MatchAddResponse response = new MatchAddResponse();
        response.buildOk();
        return response;
    }
}
