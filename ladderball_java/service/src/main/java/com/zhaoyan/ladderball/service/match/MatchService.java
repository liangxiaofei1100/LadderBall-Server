package com.zhaoyan.ladderball.service.match;

import com.zhaoyan.ladderball.dao.match.MatchDao;
import com.zhaoyan.ladderball.dao.recordermatch.RecorderMatchDao;
import com.zhaoyan.ladderball.dao.teamofmatch.TeamOfMatchDao;
import com.zhaoyan.ladderball.domain.match.db.Match;
import com.zhaoyan.ladderball.domain.match.http.MatchListRequest;
import com.zhaoyan.ladderball.domain.match.http.MatchListResponse;
import com.zhaoyan.ladderball.domain.recordermatch.db.RecorderMatch;
import com.zhaoyan.ladderball.domain.teamofmatch.db.TeamOfMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {
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

    private static BeanCopier copierMatchToMatchListResponse =
            BeanCopier.create(Match.class, MatchListResponse.Match.class, false);
    private static BeanCopier copierTeamToTeamResponse =
            BeanCopier.create(TeamOfMatch.class, MatchListResponse.Team.class, false);

    /**
     * 获取当前用户的比赛
     */
    public MatchListResponse getMatchList(MatchListRequest request) {
        MatchListResponse response = new MatchListResponse();
        response.buildOk();
        response.matches = new ArrayList<>();

        long recorderId = Long.valueOf(request.header.userToken);
        List<RecorderMatch> recorderMatches = recorderMatchDao.getRecorderMatch(recorderId);


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
                        copierTeamToTeamResponse.copy(teamHome, m.teamHome, null);
                        m.teamHome.isAsigned = recorderMatch.asignedTeam == RecorderMatch.ASIGNED_TEAM_HOME;
                    }

                    TeamOfMatch teamVisitor = teamOfMatchDao.getTeamOfMatch(match.teamVisitor);
                    if (teamVisitor != null) {
                        m.teamVisitor = new MatchListResponse.Team();
                        copierTeamToTeamResponse.copy(teamVisitor, m.teamVisitor, null);
                        m.teamVisitor.isAsigned = recorderMatch.asignedTeam == RecorderMatch.ASIGNED_TEAM_VISITOR;
                    }

                    response.matches.add(m);
                }
            }
        }

        return response;
    }
}
