<#import "parts/common.ftl" as c>

<@c.page>
    List of users

    <table border="1" cellspacing="0" cellpadding="5">
        <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Role</th>
                <th scope="col">Status</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody id="userList">
            <#list users as user>
                <tr data-id="${user.id}">
                    <td>${user.username}</td>
                    <td><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td>${user.status}</td>
                    <td><a href="/user/${user.id}">edit</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
</@c.page>
