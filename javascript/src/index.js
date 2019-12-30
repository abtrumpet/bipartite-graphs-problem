const exampleGraph = {
  nodes: [
    {id: 1, relationships: [2, 6]},
    {id: 2, relationships: [1, 3]},
    {id: 3, relationships: [2, 4]},
    {id: 4, relationships: [3, 5]},
    {id: 5, relationships: [4, 6]},
    {id: 6, relationships: [5, 1]}
  ]
};

const exampleGraph2 = {
  nodes: [
    {id: 1, relationships: [2, 6, 9]},
    {id: 2, relationships: [1, 3]},
    {id: 3, relationships: [2, 4]},
    {id: 4, relationships: [3, 5]},
    {id: 5, relationships: [4, 6]},
    {id: 6, relationships: [5, 1, 9]},
    {id: 7, relationships: [6, 8]},
    {id: 8, relationships: [5, 7]},
    {id: 9, relationships: [1, 6]}
  ]
};

function isBipartite(graph) {
  const { nodes } = graph;
  
  function _isBipartite(curNode = {}, restOfNodes = [], curKey = "a", maps = {a: [], b: []}) {
    if(curNode.relationships.some(x =>
      maps[curKey] && maps[curKey].includes(x)
    )) return false;

    if (restOfNodes.length == 0)
      return true;

    return _isBipartite(
      restOfNodes[0], 
      restOfNodes.splice(1), 
      curKey == "a" ? "b" : "a",
      {...maps, [curKey]: [...maps[curKey], curNode.id]}
    );
  }

  return _isBipartite(nodes[0], nodes.splice(1));
}

console.log(exampleGraph + "\n");
console.log(isBipartite(exampleGraph) + "\n");
console.log(exampleGraph2 + "\n");
console.log(isBipartite(exampleGraph2) + "\n");

