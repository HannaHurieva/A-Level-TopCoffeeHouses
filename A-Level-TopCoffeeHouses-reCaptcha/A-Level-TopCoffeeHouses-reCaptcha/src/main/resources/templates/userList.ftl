<#import "parts/common.ftl" as c>

<@c.page>
    List of users

    <table border="1" cellspacing="0" cellpadding="5">
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th>Status</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td>${user.status}</td>
            <td><a href="/user/${user.id}">edit</a></td>
        </tr>
    </#list>
    </tbody>
    </table>
</@c.page>