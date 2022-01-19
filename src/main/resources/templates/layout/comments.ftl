<#macro comments commentsVO account commentsTypeId commentsObjId>
    <div class="row py-2">
        <div class="col-12">
            <div class="card" style="width: 100%;">
                <div class="card-body">
                    <h5 class="card-title">评论与留言</h5>
                    <h6 class="card-subtitle mb-2 text-muted">以下内容均由网友提交发布，版权与真实性无法查证，请自行辨别。</h6>
                    <p class="card-text mb-2 text-muted">
                        本站有缓存策略，时间约2小时后能看到您的评论。本站使用自动审核机制，如果您的内容包含广告/谩骂/恐怖/暴力/涉政等不和谐内容将无法展示！</p>
                    <div class="row">
                        <div class="col-12">
                            <@commentsRecursion commentsVO></@commentsRecursion>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-12">
                            <div class="form-group row" id="replyTo" style="display: none;">
                                <label for="replyName" class="col-sm-2 col-form-label">回复</label>
                                <div class="col-sm-10">
                                    <input type="text" readonly class="form-control-plaintext"
                                           id="replyName" value="">
                                    <input type="hidden" id="replyId" value="">
                                </div>
                            </div>
                            <#if account!="null">
                                <input type="hidden" class="form-control" id="comment-nickname"
                                       value="${account.userName}">
                                <input type="hidden" class="form-control" id="comment-email" value="${account.email}">
                            <#else>
                                <div class="form-group row">
                                    <label for="comment-nickname" class="col-sm-2 col-form-label">昵称*</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="comment-nickname">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="comment-email" class="col-sm-2 col-form-label">电邮*</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="comment-email">
                                    </div>
                                </div>
                            </#if>
                            <div class="form-group row">
                                <label for="comment-link" class="col-sm-2 col-form-label">链接</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="comment-link">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="comment-content" class="col-sm-2 col-form-label">内容*</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" id="comment-content" rows="4"></textarea>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10">
                                    <button type="button" onclick="comment('${commentsTypeId}',${commentsObjId})"
                                            class="btn btn-primary btn-lg btn-block">
                                        提 交
                                    </button>
                                    <p class="card-text mb-2 text-muted">
                                        本站有缓存策略，时间约2小时后能看到您的评论。本站使用自动审核机制，如果您的内容包含广告/谩骂/恐怖/暴力/涉政等不和谐内容将无法展示！</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>
<#macro commentsRecursion commentsVO>
    <#if commentsVO?? && (commentsVO?size > 0)>
        <#list commentsVO as comment>
            <div class="media mt-3" id="cmt${comment.id!?c}">
                <div class="media-body">
                    <h5 class="mt-0">
                        <a href="${comment.link!?html}"
                           style="font-size: 14px;font-weight: 700;display: block;">
                            ${comment.author!?html}
                            <#if comment.isOwner??>
                                ${comment.isOwner?string(
                                "<span style=\"color: #ffb032;margin-left: 10px;font-weight: 900;\"><i style=\"color: #ffb032;margin-right: 0;\" class=\"fa fa-check-circle\"></i> 站点官方</span>",
                                "")}
                            </#if>
                        </a>
                        <span class="mb-2 text-muted" style="display: block;">
                            ${comment.datetime!?string["yyyy-MM-dd HH:mm:ss"]} -
                            ${comment.address!}
                        </span>
                    </h5>
                    <p style="font-size: 12px">
                        ${comment.content!?html}
                    </p>
                    <a href="javascript:replyTo('${comment.author!?html}','${comment.id!?c}')">
                    <span class="mb-2 mt-2 text-muted" style="display: block;">
                        <i class="fab fa-reply"></i>回复
                    </span>
                    </a>
                    <#if comment.child?? && (comment.child?size > 0)>
                        <@commentsRecursion comment.child></@commentsRecursion>
                    </#if>
                </div>
            </div>
        </#list>
    </#if>
</#macro>