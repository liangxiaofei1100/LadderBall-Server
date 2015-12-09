package com.zhaoyan.ladderball.service.match;

import com.zhaoyan.ladderball.dao.match.TmpMatchDao;
import com.zhaoyan.ladderball.dao.player.TmpPlayerOfMatchDao;
import com.zhaoyan.ladderball.dao.recordermatch.RecorderTmpMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TmpTeamOfMatchDao;
import com.zhaoyan.ladderball.domain.match.db.TmpMatch;
import com.zhaoyan.ladderball.domain.match.http.*;
import com.zhaoyan.ladderball.domain.player.db.TmpPlayerOfMatch;
import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderMatch;
import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderTmpMatch;
import com.zhaoyan.ladderball.domain.teamofmatch.db.TmpTeamOfMatch;
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
public class TmpMatchService extends BaseService{
    Logger logger = LoggerFactory.getLogger(TmpMatchService.class);

    @Autowired
    @Qualifier("hibernateRecorderTmpMatchDao")
    RecorderTmpMatchDao recorderTmpMatchDao;

    @Autowired
    @Qualifier("hibernateTmpMatchDao")
    TmpMatchDao tmpMatchDao;

    @Autowired
    @Qualifier("hibernateTmpTeamOfMatchDao")
    TmpTeamOfMatchDao tmpTeamOfMatchDao;

    @Autowired
    @Qualifier("hibernateTmpPlayerOfMatchDao")
    TmpPlayerOfMatchDao tmpPlayerOfMatchDao;

    private static BeanCopier copierTmpMatchToTmpMatchListResponse =
            BeanCopier.create(TmpMatch.class, TmpMatchListResponse.Match.class, false);
    private static BeanCopier copierTmpTeamOfMatchToTmpMatchListResponse =
            BeanCopier.create(TmpTeamOfMatch.class, TmpMatchListResponse.Team.class, false);
    private static BeanCopier copierTmpTeamOfMatchToTmpMatchDetailResponse =
            BeanCopier.create(TmpTeamOfMatch.class, TmpMatchDetailResponse.Team.class, false);
    private static BeanCopier copierTmpPlayerOfMatchToTmpMatchDetailResponse =
            BeanCopier.create(TmpPlayerOfMatch.class, TmpMatchDetailResponse.Player.class, false);

    /**
     * 获取练习赛列表
     */
    public TmpMatchListResponse getTmpMatchList(TmpMatchListRequest request) {
        TmpMatchListResponse response = new TmpMatchListResponse();
        response.buildOk();
        response.matches = new ArrayList<>();

        long recorderId = getRecorderIdFromUserToken(request.header.userToken);
        List<RecorderTmpMatch> recorderTmpMatches = recorderTmpMatchDao.getRecorderTmpMatchByRecorder(recorderId);

        if (recorderTmpMatches != null) {
            for (RecorderTmpMatch recorderTmpMatch : recorderTmpMatches) {
                logger.debug("getTmpMatchList(), recorderTmpMatch: " + recorderTmpMatch);
                TmpMatch tmpMatch = tmpMatchDao.getMatch(recorderTmpMatch.matchId);

                if (tmpMatch != null) {
                    TmpMatchListResponse.Match match = new TmpMatchListResponse.Match();
                    copierTmpMatchToTmpMatchListResponse.copy(tmpMatch, match, null);
                    match.startTime = tmpMatch.startTime.getTime();

                    // 获取主队和客队信息
                    TmpTeamOfMatch tmpTeamHome = tmpTeamOfMatchDao.getTmpTeamOfMatch(tmpMatch.teamHome);
                    if (tmpTeamHome != null) {
                        match.teamHome = new TmpMatchListResponse.Team();
                        copierTmpTeamOfMatchToTmpMatchListResponse.copy(tmpTeamHome, match.teamHome, null);
                        match.teamHome.isAsigned = recorderTmpMatch.asignedTeam == RecorderTmpMatch.ASIGNED_TEAM_HOME;
                    }

                    TmpTeamOfMatch tmpTeamVisitor = tmpTeamOfMatchDao.getTmpTeamOfMatch(tmpMatch.teamVisitor);
                    if (tmpTeamVisitor != null) {
                        match.teamVisitor = new TmpMatchListResponse.Team();
                        copierTmpTeamOfMatchToTmpMatchListResponse.copy(tmpTeamVisitor, match.teamVisitor, null);
                        match.teamVisitor.isAsigned = recorderTmpMatch.asignedTeam == RecorderTmpMatch.ASIGNED_TEAM_VISITOR;
                    }

                    response.matches.add(match);
                }
            }
        }

        return response;
    }

    /**
     * 获取练习赛详情
     */
    public TmpMatchDetailResponse getTmpMatchDetail(TmpMatchDetailRequest request) {
        TmpMatchDetailResponse response = new TmpMatchDetailResponse();

        TmpMatch match = tmpMatchDao.getMatch(request.matchId);
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
            RecorderTmpMatch recorderMatch = recorderTmpMatchDao.getRecorderTmpMatchByRecorderMatch(recorderId, request.matchId);

            // 获取主队和客队信息
            TmpTeamOfMatch teamHome = tmpTeamOfMatchDao.getTmpTeamOfMatch(match.teamHome);
            if (teamHome != null) {
                response.teamHome = new TmpMatchDetailResponse.Team();
                copierTmpTeamOfMatchToTmpMatchDetailResponse.copy(teamHome, response.teamHome, null);
                if (recorderMatch != null) {
                    response.teamHome.isAsigned = recorderMatch.asignedTeam == RecorderMatch.ASIGNED_TEAM_HOME;
                    if (response.teamHome.isAsigned) {
                        response.teamHome.players = getPlayers(response.teamHome.id);
                    }
                }
            }

            TmpTeamOfMatch teamVisitor = tmpTeamOfMatchDao.getTmpTeamOfMatch(match.teamVisitor);
            if (teamHome != null) {
                response.teamVisitor = new TmpMatchDetailResponse.Team();
                copierTmpTeamOfMatchToTmpMatchDetailResponse.copy(teamVisitor, response.teamVisitor, null);
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
                TmpMatchDetailResponse.PartData partData = new TmpMatchDetailResponse.PartData();
                partData.partNumber = i;
                partData.isComplete = false;

                response.partDatas.add(partData);
            }
        } else {
            response.buildFail("没有找到比赛");
        }

        return response;
    }

    private List<TmpMatchDetailResponse.Player> getPlayers(long teamId) {
        List<TmpMatchDetailResponse.Player> players = new ArrayList<>();

        List<TmpPlayerOfMatch> playerOfMatches = tmpPlayerOfMatchDao.getPlayerByTeam(teamId);
        if (playerOfMatches != null) {
            for (TmpPlayerOfMatch p : playerOfMatches) {
                TmpMatchDetailResponse.Player player = new TmpMatchDetailResponse.Player();
                copierTmpPlayerOfMatchToTmpMatchDetailResponse.copy(p, player, null);
                players.add(player);
            }
        } else {
            logger.debug("getMatchDetail() can not find player of team: " + teamId);
        }

        return players;
    }
}
