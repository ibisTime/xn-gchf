package com.cdkj.gchf.dto.req;

/**
 * 人员补录技能清单
 * @author: silver 
 * @since: 2018年6月28日 下午1:58:45 
 * @history:
 */
public class XN631413ReqSkill {

    // 技能
    private String name;

    // 技能证书
    private String pdf;

    // 技能评分
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
