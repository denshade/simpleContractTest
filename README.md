This is a simple contract testing library.

Conventions are:
You use a file broker. 
Store/update your contracts in a json file on a shared github/gitlab repository. 

Each contract contains multiple contract paragraphs.
Each contract contract consists of: 
{
"contractsVersion": "1.2.3"
"contractParagraphs":[ 
  {
  "expectedInput": "...",
  "expectedOutput": "...",
  "expectedState": "optionalStatename",
  "expectedEndpointUrlSuffix" : "/v1/customers",
  "expectedEndpointVerb" : "GET",
  "expectedEndpointCode" : "200"
  }
]
}
