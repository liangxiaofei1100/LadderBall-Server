package com.zhaoyan.ladderball.domain.wexinuserfootballteam.db;

import com.zhaoyan.ladderball.domain.account.db.WeiXinUser;
import com.zhaoyan.ladderball.domain.team.db.FootballTeam;

import javax.persistence.*;

@Entity(name = "weixinuser_footballteam")
public class WeiXinUserFooballTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @ManyToOne
    @JoinColumn(name = "weixinuser_id")
    public WeiXinUser weiXinUser;

    @ManyToOne
    @JoinColumn(name = "footballteam_id")
    public FootballTeam footballTeam;

    /**
     * 是否是队长
     */
    @Column(name = "is_captain")
    public boolean isCaptain;
}
