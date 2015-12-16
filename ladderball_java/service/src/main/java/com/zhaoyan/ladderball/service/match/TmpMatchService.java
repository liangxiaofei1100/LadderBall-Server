package com.zhaoyan.ladderball.service.match;

import com.zhaoyan.ladderball.dao.match.TmpMatchDao;
import com.zhaoyan.ladderball.dao.match.TmpMatchPartDao;
import com.zhaoyan.ladderball.dao.player.TmpPlayerOfMatchDao;
import com.zhaoyan.ladderball.dao.recordermatch.RecorderTmpMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TmpTeamOfMatchDao;
import com.zhaoyan.ladderball.domain.match.db.TmpMatch;
import com.zhaoyan.ladderball.domain.match.db.TmpMatchPart;
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
    @Qualifier("hibernateTmpMatchPartDao")
    TmpMatchPartDao tmpMatchPartDao;

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
    private static BeanCopier copierMatchModifyRequestToTmpPlayerOfMatch =
            BeanCopier.create(MatchModifyRequest.Player.class, TmpPlayerOfMatch.class, false);

    // 添加球员
    private static BeanCopier copierMatchAddPlayerRequestToTmpPlayerOfMatch =
            BeanCopier.create(MatchAddPlayerRequest.Player.class, TmpPlayerOfMatch.class, false);
    private static BeanCopier copierTmpPlayerOfMatchToMatchAddPlayerResponse =
            BeanCopier.create(TmpPlayerOfMatch.class, MatchAddPlayerResponse.Player.class, false);

    // 添加比赛
    private static BeanCopier copierMatchAddRequestToTmpMatch =
            BeanCopier.create(MatchAddRequest.class, TmpMatch.class, false);

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
                    TmpTeamOfMatch tmpTeamHome = tmpTeamOfMatchDao.getTeamOfMatch(tmpMatch.teamHome);
                    if (tmpTeamHome != null) {
                        match.teamHome = new MatchListResponse.Team();
                        copierTmpTeamOfMatchToMatchListResponse.copy(tmpTeamHome, match.teamHome, null);
                        match.teamHome.isAsigned = recorderTmpMatch.asignedTeam == RecorderTmpMatch.ASIGNED_TEAM_HOME;
                    }

                    TmpTeamOfMatch tmpTeamVisitor = tmpTeamOfMatchDao.getTeamOfMatch(tmpMatch.teamVisitor);
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
            TmpTeamOfMatch teamHome = tmpTeamOfMatchDao.getTeamOfMatch(match.teamHome);
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

            TmpTeamOfMatch teamVisitor = tmpTeamOfMatchDao.getTeamOfMatch(match.teamVisitor);
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

            // 处理小节数据
            response.partDatas = new ArrayList<>();
            List<TmpMatchPart> matchParts = tmpMatchPartDao.getMatchParts(request.matchId);

            // 修复小节数据错误，因为老数据中没有建立小节表，却有小节数，补上小节数据
            // 修复部分开始
            if (match.totalPart > matchParts.size()) {
                // 节数增加了
                for (int i = matchParts.size() + 1; i <= match.totalPart; i++) {
                    TmpMatchPart matchPart = new TmpMatchPart();
                    matchPart.matchId = request.matchId;
                    matchPart.partNumber = i;
                    matchPart.isComplete = false;
                    tmpMatchPartDao.addMatchPart(matchPart);
                }
            }
            // 修复部分结束

            // 小节数据
            for (TmpMatchPart part : matchParts) {
                MatchDetailResponse.PartData partData = new MatchDetailResponse.PartData();
                partData.partNumber = part.partNumber;
                partData.isComplete = part.isComplete;

                response.partDatas.add(partData);
            }

            response.buildOk();
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
    public MatchModifyResponse modifyMatch(MatchModifyRequest request) {
        MatchModifyResponse response = new MatchModifyResponse();
        response.buildOk();

        // 修改比赛表
        TmpMatch match = tmpMatchDao.getMatch(request.matchId);
        if (match == null) {
            logger.warn("modifyTmpMatch() modify match fail. matchId: " + request.matchId);
            response.buildFail();
            return response;
        } else {
            match.playerNumber = request.playerNumber;
            match.totalPart = request.totalPart;
            match.partMinutes = request.partMinutes;
            tmpMatchDao.modifyMatch(match);
        }

        // 修改比赛小节数据
        List<TmpMatchPart> matchParts = tmpMatchPartDao.getMatchParts(request.matchId);
        if (request.totalPart > matchParts.size()) {
            // 节数增加了
            for (int i = matchParts.size() + 1; i <= request.totalPart; i++) {
                TmpMatchPart matchPart = new TmpMatchPart();
                matchPart.matchId = request.matchId;
                matchPart.partNumber = i;
                matchPart.isComplete = false;
                tmpMatchPartDao.addMatchPart(matchPart);
            }
        } else if (request.totalPart < matchParts.size()) {
            // 节数减少了
            for (TmpMatchPart matchPart : matchParts) {
                if (matchPart.partNumber > request.totalPart) {
                    tmpMatchPartDao.deleteMatchPart(matchPart);
                }
            }
        }

        // 修改球员数据
        for (MatchModifyRequest.Player player : request.players) {
            TmpPlayerOfMatch newPlayer = tmpPlayerOfMatchDao.getPlayerById(player.id);
            if (newPlayer != null) {
                copierMatchModifyRequestToTmpPlayerOfMatch.copy(player, newPlayer, null);
                tmpPlayerOfMatchDao.modifyPlayer(newPlayer);
            } else {
                logger.warn("modifyTmpMatch() error, player id not found. playerId: " + player.id);
            }
        }
        return response;
    }

    /**
     * 添加一场练习赛
     */
    public MatchAddResponse addMatch(MatchAddRequest request) {
        // 添加主队
        TmpTeamOfMatch teamHome = new TmpTeamOfMatch();
        teamHome.name = request.teamHomeName;
        tmpTeamOfMatchDao.addTeamOfMatch(teamHome);
        // 添加客队
        TmpTeamOfMatch teamVisitor = new TmpTeamOfMatch();
        teamVisitor.name = request.teamVisitorName;
        tmpTeamOfMatchDao.addTeamOfMatch(teamVisitor);
        // 添加比赛
        TmpMatch match = new TmpMatch();
        copierMatchAddRequestToTmpMatch.copy(request, match, null);
        match.teamHome = teamHome.id;
        match.teamVisitor = teamVisitor.id;
        tmpMatchDao.addMatch(match);
        // 将比赛分配给记录者，并且认领主队
        RecorderTmpMatch recorderTmpMatch = new RecorderTmpMatch();
        recorderTmpMatch.recorderId = getRecorderIdFromUserToken(request.header.userToken);
        recorderTmpMatch.matchId = match.id;
        recorderTmpMatch.asignedTeam = RecorderTmpMatch.ASIGNED_TEAM_HOME;
        recorderTmpMatchDao.addRecorderTmpMatch(recorderTmpMatch);

        MatchAddResponse response = new MatchAddResponse();
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
                    TmpTeamOfMatch tmpTeamHome = tmpTeamOfMatchDao.getTeamOfMatch(tmpMatch.teamHome);
                    if (tmpTeamHome != null) {
                        match.teamHome = new MatchListResponse.Team();
                        copierTmpTeamOfMatchToMatchListResponse.copy(tmpTeamHome, match.teamHome, null);
                        match.teamHome.isAsigned = recorderTmpMatch.asignedTeam == RecorderTmpMatch.ASIGNED_TEAM_HOME;
                    }

                    TmpTeamOfMatch tmpTeamVisitor = tmpTeamOfMatchDao.getTeamOfMatch(tmpMatch.teamVisitor);
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
     * 记录者领取练习赛，只能领取客场队伍
     */
    public MatchAsignVisitorResponse asignTmpMatchVisitor(MatchAsignVisitorRequest request) {
        MatchAsignVisitorResponse response = new MatchAsignVisitorResponse();

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

    /**
     * 添加球员
     */
    public MatchAddPlayerResponse addPlayer(MatchAddPlayerRequest request) {
        MatchAddPlayerResponse response = new MatchAddPlayerResponse();

        TmpPlayerOfMatch playerOfMatch = new TmpPlayerOfMatch();
        copierMatchAddPlayerRequestToTmpPlayerOfMatch.copy(request.player, playerOfMatch, null);
        playerOfMatch.teamId = request.teamId;

        // 不能添加重复号码的球员
        boolean isRepeatedNumber = tmpPlayerOfMatchDao.isPlayerNumberRepeated(playerOfMatch);
        if (isRepeatedNumber) {
            response.buildFail("不能添加重复号码的球员");
        } else {
            tmpPlayerOfMatchDao.addPlayer(playerOfMatch);

            // 将结果添加到response
            MatchAddPlayerResponse.Player resultPlayer = new MatchAddPlayerResponse.Player();
            copierTmpPlayerOfMatchToMatchAddPlayerResponse.copy(playerOfMatch, resultPlayer, null);

            response.player = resultPlayer;
            response.buildOk();
        }
        return response;
    }
}
