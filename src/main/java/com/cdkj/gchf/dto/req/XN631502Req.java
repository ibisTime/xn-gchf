package com.cdkj.gchf.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改技能
 * @author: silver 
 * @since: 2018年7月12日 下午3:36:26 
 * @history:
 */
public class XN631502Req {
    // 技能编号
    @NotBlank
    private String code;

    // 技能
    @NotBlank
    private String name;

    // 技能证书
    private String pdf;

    // 技能评分
    private String score;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
