<!DOCTYPE html>
<html>
  <head>
    <title>Schema.org master file: RDFS in RDFa</title>
    <meta charset="UTF-8" />
    <style type="text/css">
      span.h {
        padding-left: 0px;
        font-weight: bold;
      }
      span {
        display: block;
        padding-left: 10px;
      }
    </style>
  </head>

  <body>
    <h1>${title}</h1>

    <p>This is an RDFa-based representation of the schema.org schema, the underlying representation of the schema.org vocabulary.</p>

    <p>It is represented in a form based on W3C RDF/RDFS. We encourage proposals for schema.org improvements to be expressed
      in this same style. See the <a href="http://schema.org/docs/howwework.html">how we work</a> document for information on collaboration.</p>
    <p>
    See <a href="http://schema.org/docs/datamodel.html">datamodel</a> for more details,
    the <a href="http://schema.org/docs/developers.html">developers</a> page for alternate formats.
    </p>
    <p>
      Note: the style of RDFa used here may change in the future. To see the substantive content of the schema, view the
      HTML source markup. We use a simple subset of RDFa for syntax, including prefixes that are declared in the
      <a href="http://www.w3.org/2011/rdfa-context/rdfa-1.1">RDFa initial context</a>. We also use Markdown syntax within comments to
      make hypertext documentation easier to edit. Other <a href="http://schema.org/docs/developers.html#formats">formats</a> are available.
    </p>

    <hr />

   	<#list resources as resource>
      <div typeof="${resource.type}" resource="${resource.uri}">
      	<#list resource.predicates as predicate>
      		<#if predicate.type == "1">
      			<span <#if predicate.styleClass != "">class="h"</#if> property="${predicate.property}">${predicate.value}</span>
      		</#if>
      		<#if predicate.type == "2">
      			<link property="${predicate.property}" href="${predicate.href}" />
      		</#if>
      		<#if predicate.type == "3">
      			<span>${predicate.text} <a property="${predicate.property}" href="${predicate.href}">${predicate.value}</a></span>
      		</#if>
      	</#list>
      </div>
    </#list>
   

</body>
</html>
