<#import "parts/common.ftl" as c>


<@c.page>
<div class="form-row">
    <form method="get" action="/main" class="form-inline">
        <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search info">
        <button class="btn btn-primary ml-2" type="submit">Search</button>
    </form>
</div>
	<div>
        <form method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="text" name="name" placeholder="Input your login"/>
            <input type="text" name="sirname" placeholder="Enter your Password"/>
            <input type="file" name="file"/>
            <button type="submit">Enter the information to data base</button>
        </form>
    </div>

   		<#list users as user>
		<div>	
			<b>${user.id}</b>
            <span>${user.name}</span>
            <i>${user.sirname}</i>
            <strong>${user.authorName}</strong>
            <div>
				<#if user.filename??>
                    <img src="/img/${user.filename}"
				</#if>
            </div>
		</div>
		<#else>
		No message
		</#list>
		
</@c.page>