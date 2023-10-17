

export function toCamel(o:any) {

  var newO, origKey, newKey, value
  if (o instanceof Array) {
    return o.map(function(value) {
        if (value!==null && typeof value === "object") {
          value = toCamel(value)
        }
        return value
    })
  } else {
    newO = {}
    for (origKey in o) {
      if (o.hasOwnProperty(origKey)) {
        newKey = _.camelCase(origKey)
        value = o[origKey]
        if (value instanceof Array || (value  && value.constructor === Object)) {
            //A VOIR en fonction implementation : if isArrayOfnotObject alors arr.map(_.camelCase(value))

          value = toCamel(value)
        }else{
            
       
            if(typeof(value)== "string" ) {value=_.camelCase(value)}
         
        }
   newO[newKey] = value
      }
    }
  }
  return newO
}


