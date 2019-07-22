<#import "parts/common.ftl" as c>

<@c.page>
    <div class="mb-1">Registration form</div>
    ${message?ifExists}
    <form action="/registration" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> First Name : <input type="text" name="firstName"/> </label></div>
        <div><label> Last Name : <input type="text" name="lastName"/> </label></div>
        <div><label> Email : <input type="email" name="email"/> </label></div>
        <div><label> Password : <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Registration"/></div>
    </form>
</@c.page>