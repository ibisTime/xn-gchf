package com.cdkj.gchf.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.base.Paginable;
import com.cdkj.gchf.domain.Progress;
import com.cdkj.gchf.dto.req.XN631380Req;
import com.cdkj.gchf.dto.req.XN631382Req;

@Component
public interface IProgressAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addProgress(XN631380Req req);

    public int editProgress(XN631382Req req);

    public Paginable<Progress> queryProgressPage(int start, int limit,
            Progress condition);

    public List<Progress> queryProgressList(Progress condition);

    public Progress getProgress(String code);

}
