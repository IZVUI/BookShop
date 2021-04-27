<#import "parts/common.ftlh" as c>

<@c.page>
User editor

    <form action="/user" method="post">
        <input type="text" name="username" value="${user.username}">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="text" value="${user.id}" name="userId" hidden>
        <input type="text" value="${_csrf.token}" name="_csrf" hidden>
        <button type="submit">Save</button>
    </form>

</@c.page>