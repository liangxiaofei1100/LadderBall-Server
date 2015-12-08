package com.zhaoyan.ladderball.dao.match;

import com.zhaoyan.ladderball.domain.match.db.Match;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * match table
 */
public interface MatchDao {

    /**
     * 查询所有比赛
     */
    List<Match> getAllMatch();

    /**
     * 查询一场比赛
     */
    Match getMatch(long id);

    /**
     * 修改一场比赛
     */
    boolean modifyMatch(long id, int playerNumber, int totalPart, int partMinutes);
}
