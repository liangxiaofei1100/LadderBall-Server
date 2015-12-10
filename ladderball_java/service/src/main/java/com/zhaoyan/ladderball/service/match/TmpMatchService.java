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
public class TmpMatchService extends BaseService {
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

    private static BeanCopier copierTmpMatchToMatchListResponse =
            BeanCopier.create(TmpMatch.class, MatchListResponse.Match.class, false);
    private static BeanCopier copierTmpTeamOfMatchToMatchListResponse =
            BeanCopier.create(TmpTeamOfMatch.class, MatchListResponse.Team.class, false);
    private static BeanCopier copierTmpTeamOfMatchToMatchDetailResponse =
            BeanCopier.create(TmpTeamOfMatch.class, MatchDetailResponse.Team.class, false);
    private static BeanCopier copierTmpPlayerOfMatchToMatchDetailResponse =
            BeanCopier.create(TmpPlayerOfMatch.class, MatchDetailResponse.Player.class, false);
    private static BeanCopier copierTmpMatchModifyRequestToTmpPlayerOfMatch =
            BeanCopier.create(TmpMatchModifyRequest.Player.class, TmpPlayerOfMatch.class, false);

    /**
     * 获取练习赛列表
     */
    public MatchListResponse getMatchList(MatchListRequest request) {
        MatchListResponse response = new MatchListResponse();
        response.buildOk();
        response.matches = new ArrayList<>();

        long recorderId = getRecorderIdFromUserToken(request.header.userToken);
        List<RecorderTmpMatch> recorderTmpMatches = recorderTmpMatchDao.getRecorderTmpMatchByRecorder(recorderId);

        if (recorderTmpMatches != null) {
            for (RecorderTmpMatch recorderTmpMatch : recorderTmpMatches) {
                logger.debug("getTmpMatchList(), recorderTmpMatch: " + recorderTmpMatch);
                TmpMatch tmpMatch = tmpMatchDao.getMatch(recorderTmpMatch.matchId);

                if (tmpMatch != null) {
                    MatchListResponse.Match match = new MatchListResponse.Match();
                    copierTmpMatchToMatchListResponse.copy(tmpMatch, match, null);
                    if (tmpMatch.startTime != null) {
                        match.startTime = tmpMatch.startTime.getTime();
                    }

                    // 获取主队和客队信息
                    TmpTeamOfMatch tmpTeamHome = tmpTeamOfMatchDao.getTmpTeamOfMatch(tmpMatch.teamHome);
                    if (tmpTeamHome != null) {
                        match.teamHome = new MatchListResponse.Team();
                        copierTmpTeamOfMatchToMatchListResponse.copy(tmpTeamHome, match.teamHome, null);
                        match.teamHome.isAsigned = recorderTmpMatch.asignedTeam == RecorderTmpMatch.ASIGNED_TEAM_HOME;
                    }

                    TmpTeamOfMatch tmpTeamVisitor = tmpTeamOfMatchDao.getTmpTeamOfMatch(tmpMatch.teamVisitor);
                    if (tmpTeamVisitor != null) {
                        match.teamVisitor = new MatchListResponse.Team();
                        copierTmpTeamOfMatchToMatchListResponse.copy(tmpTeamVisitor, match.teamVisitor, null);
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
    public MatchDetailResponse getMatchDetail(MatchDetailRequest request) {
        MatchDetailResponse response = new MatchDetailResponse();

        TmpMatch match = tmpMatchDao.getMatch(request.matchId);
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
            RecorderTmpMatch recorderMatch = recorderTmpMatchDao.getRecorderTmpMatchByRecorderMatch(recorderId, request.matchId);

            // 获取主队和客队信息
            TmpTeamOfMatch teamHome = tmpTeamOfMatchDao.getTmpTeamOfMatch(match.teamHome);
            if (teamHome != null) {
                response.teamHome = new MatchDetailResponse.Team();
                copierTmpTeamOfMatchToMatchDetailResponse.copy(teamHome, response.teamHome, null);
                if (recorderMatch != null) {
                    response.teamHome.isAsigned = recorderMatch.asignedTeam == RecorderMatch.ASIGNED_TEAM_HOME;
                    if (response.teamHome.isAsigned) {
                        response.teamHome.players = getPlayers(response.teamHome.id);
                    }
                }
            }

            TmpTeamOfMatch teamVisitor = tmpTeamOfMatchDao.getTmpTeamOfMatch(match.teamVisitor);
            if (teamHome != null) {
                response.teamVisitor = new MatchDetailResponse.Team();
                copierTmpTeamOfMatchToMatchDetailResponse.copy(teamVisitor, response.teamVisitor, null);
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
            for (int i = 1; i <= response.totalPart; i++) {
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

    private List<MatchDetailResponse.Player> getPlayers(long teamId) {
        List<MatchDetailResponse.Player> players = new ArrayList<>();

        List<TmpPlayerOfMatch> playerOfMatches = tmpPlayerOfMatchDao.getPlayerByTeam(teamId);
        if (playerOfMatches != null) {
            for (TmpPlayerOfMatch p : playerOfMatches) {
                MatchDetailResponse.Player player = new MatchDetailResponse.Player();
                copierTmpPlayerOfMatchToMatchDetailResponse.copy(p, player, null);
                players.add(player);
            }
        } else {
            logger.debug("getMatchDetail() can not find player of team: " + teamId);
        }

        return players;
    }

    /**
     * 修改练习赛
     */
    public TmpMatchModifyResponse modifyTmpMatch(TmpMatchModifyRequest request) {
        TmpMatchModifyResponse response = new TmpMatchModifyResponse();
        response.buildOk();
        boolean result = tmpMatchDao.modifyMatch(request.matchId, request.playerNumber, request.totalPart, request.partMinutes);
        if (!result) {
            logger.warn("modifyTmpMatch() modify match fail. matchId: " + request.matchId);
            response.buildFail();
            return response;
        }

        for (TmpMatchModifyRequest.Player player : request.players) {
            TmpPlayerOfMatch newPlayer = new TmpPlayerOfMatch();
            copierTmpMatchModifyRequestToTmpPlayerOfMatch.copy(player, newPlayer, null);
            boolean playerResult = tmpPlayerOfMatchDao.modifyPlayer(newPlayer);
            if (!playerResult) {
                logger.warn("modifyTmpMatch() modify player fail. playerId: " + player.id);
            }
        }
        return response;
    }

    /**
     * 添加一场练习赛
     */
    public AddTmpMatchResponse addMatch(AddTmpMatchRequest request) {
        // 添加主队
        TmpTeamOfMatch teamHome = new TmpTeamOfMatch();
        teamHome.name = request.teamHomeName;
        tmpTeamOfMatchDao.addTmpTeamOfMatch(teamHome);
        // 添加客队
        TmpTeamOfMatch teamVisitor = new TmpTeamOfMatch();
        teamVisitor.name = request.teamVisitorName;
        tmpTeamOfMatchDao.addTmpTeamOfMatch(teamVisitor);
        // 添加比赛
        TmpMatch match = new TmpMatch();
        match.teamHome = teamHome.id;
        match.teamVisitor = teamVisitor.id;
        match.playerNumber = request.playerNumber;
        tmpMatchDao.addMatch(match);
        // 将比赛分配给记录者，并且认领主队
        RecorderTmpMatch recorderTmpMatch = new RecorderTmpMatch();
        recorderTmpMatch.recorderId = getRecorderIdFromUserToken(request.header.userToken);
        recorderTmpMatch.matchId = match.id;
        recorderTmpMatch.asignedTeam = RecorderTmpMatch.ASIGNED_TEAM_HOME;
        recorderTmpMatchDao.addRecorderTmpMatch(recorderTmpMatch);

        AddTmpMatchResponse response = new AddTmpMatchResponse();
        response.buildOk();
        return response;
    }

    /**
     * 获取未领取的练习赛
     */
    public MatchListResponse getMatchToAsignList(MatchListRequest request) {
        MatchListResponse response = new MatchListResponse();
        response.buildOk();
        response.matches = new ArrayList<>();

        long recorderId = getRecorderIdFromUserToken(request.header.userToken);
        List<RecorderTmpMatch> recorderTmpMatches = recorderTmpMatchDao.getToAsignTmpMatchByRecorder(recorderId);

        if (recorderTmpMatches != null) {
            for (RecorderTmpMatch recorderTmpMatch : recorderTmpMatches) {
                logger.debug("getMatchToAsignList(), recorderTmpMatch: " + recorderTmpMatch);
                TmpMatch tmpMatch = tmpMatchDao.getMatch(recorderTmpMatch.matchId);

                if (tmpMatch != null) {
                    MatchListResponse.Match match = new MatchListResponse.Match();
                    copierTmpMatchToMatchListResponse.copy(tmpMatch, match, null);
                    if (tmpMatch.startTime != null) {
                        match.startTime = tmpMatch.startTime.getTime();
                    }

                    // 获取主队和客队信息
                    TmpTeamOfMatch tmpTeamHome = tmpTeamOfMatchDao.getTmpTeamOfMatch(tmpMatch.teamHome);
                    if (tmpTeamHome != null) {
                        match.teamHome = new MatchListResponse.Team();
                        copierTmpTeamOfMatchToMatchListResponse.copy(tmpTeamHome, match.teamHome, null);
                        match.teamHome.isAsigned = recorderTmpMatch.asignedTeam == RecorderTmpMatch.ASIGNED_TEAM_HOME;
                    }

                    TmpTeamOfMatch tmpTeamVisitor = tmpTeamOfMatchDao.getTmpTeamOfMatch(tmpMatch.teamVisitor);
                    if (tmpTeamVisitor != null) {
                        match.teamVisitor = new MatchListResponse.Team();
                        copierTmpTeamOfMatchToMatchListResponse.copy(tmpTeamVisitor, match.teamVisitor, null);
                        match.teamVisitor.isAsigned = recorderTmpMatch.asignedTeam == RecorderTmpMatch.ASIGNED_TEAM_VISITOR;
                    }

                    response.matches.add(match);
                }
            }
        }

        return response;
    }

    /**
     *记录者领取练习赛，只能领取客场队伍
     */
    public TmpMatchAsignVisitorResponse asignTmpMatchVisitor(TmpMatchAsignVisitorRequest request) {
        TmpMatchAsignVisitorResponse response = new TmpMatchAsignVisitorResponse();

        long recorderId = getRecorderIdFromUserToken(request.header.userToken);
        boolean isAlreadyAsigned = recorderTmpMatchDao.isTmpMatchAsignedToRecorder(recorderId, request.matchId);
        if (isAlreadyAsigned) {
            response.buildFail("已经认领过该比赛");
        } else {
            boolean result = recorderTmpMatchDao.asignTmpMatchVisitor(recorderId, request.matchId);
            if (result) {
                response.buildOk();
            } else {
                response.buildFail("认领失败");
            }
        }

        return response;
    }
}
