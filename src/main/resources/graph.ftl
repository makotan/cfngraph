digraph G {
  graph [
    rankdir = "LR"
  ];
  node [shape = record , style = filled];
  subgraph Outputs {
  <#list template.outputs as node>
    ${node.name} [label = "<f0> ${node.name} | <f1> ${node.typeName!""} | <f2> Outputs" fillcolor="${node.getColorString()}"];
    <#list node.edgeList as edge>
    ${edge.fromName} -> "${edge.toName}" [label="${edge.title}" color="${edge.getColorString()}"];
    </#list>

  </#list>
  }


  subgraph Conditions {
  <#list template.conditions as node>
    ${node.name} [label = "<f0> ${node.name} | <f1> ${node.typeName!""} | <f2> Parameters" fillcolor="${node.getColorString()}"];
    <#list node.edgeList as edge>
    ${edge.fromName} -> "${edge.toName}" [label="${edge.title}" color="${edge.getColorString()}"];
    </#list>

  </#list>
  }

  subgraph Parameters {
    <#list template.parameters as node>
    ${node.name} [label = "<f0> ${node.name} | <f1> ${node.typeName!""} | <f2> Parameters" fillcolor="${node.getColorString()}"];
        <#list node.edgeList as edge>
        ${edge.fromName} -> "${edge.toName}" [label="${edge.title}" color="${edge.getColorString()}"];
        </#list>

    </#list>
  }
  subgraph Mappings {
    <#list template.mappings as node>
    ${node.name} [label = "<f0> ${node.name} | <f1>  | <f2> Mappings" fillcolor="${node.getColorString()}"];
        <#list node.edgeList as edge>
        ${edge.fromName} -> "${edge.toName}" [label="${edge.title}" color="${edge.getColorString()}"];
        </#list>

    </#list>
  }
  subgraph Resources {
    <#list template.resources as node>
    ${node.name} [label = "<f0> ${node.name} | <f1> ${node.typeName!""} | <f2> Resources" fillcolor="${node.getColorString()}"];
        <#list node.edgeList as edge>
        ${edge.fromName} -> "${edge.toName}" [label="${edge.title}" color="${edge.getColorString()}"];
        </#list>

    </#list>
  }
}
