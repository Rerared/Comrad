<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as lgn>

<@c.page>
<div class="mb-1">
    Add new user
</div>
    ${user?ifExists}
    <@lgn.login "/registration" true />
</@c.page>