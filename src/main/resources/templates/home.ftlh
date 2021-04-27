<#import "parts/common.ftlh" as c>


<@c.page>
    <div class="cols-2">
        <form method="get" action="/home" class="row row-cols-lg-auto g-3 align-items-center">
            <div class="form-group col-md-5">
                <input type="text" class="form-control" name="filter" placeholder="Search by name"
                       value="${filter?ifExists}">
                <button type="submit" class="btn btn-primary m-1">Search</button>
            </div>
        </form>

    </div>

    <a class="btn btn-primary mt-3" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new book
    </a>

    <div class="collapse mt-2 mb-2" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <input type="description" class="form-control" name="description" placeholder="Enter description">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" id="customFile" class="form-control" name="file">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary m-1">Add</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-group">
        <#list books as book>
            <div class="card my-3" >
                <#if book??>
                    <#if book.filename??>
                        <img src="/img/${book.filename}" class="card-img-top">
                    </#if>
                    <div class="card-body">
                        <span class="card-title1">${book.name?ifExists}</span>
                        <div class="card-footer text-muted">
                            <span>${book.description?ifExists}</span>
                        </div>
                    </div>
                </#if>
            </div>
        <#else >
            No books
        </#list>
    </div>

</@c.page>