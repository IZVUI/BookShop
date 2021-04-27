<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-6">
                <input type="text" name="username" placeholder="User name"/>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" placeholder="Password"/>
            </div>
        </div>
        <#if !isRegisterForm>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label"> Email: </label>
            <div class="col-sm-6">
                <input type="email" name="email" placeholder="some@some.com"/>
            </div>
        </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">Sign in</a></#if>
        <button type="submit" class="btn btn-primary m-1" ><#if isRegisterForm>Sign in <#else> Log In</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-primary m-1" >Log Out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>