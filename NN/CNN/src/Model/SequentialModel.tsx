import * as tf from '@tensorflow/tfjs'
import * as tfvis from "@tensorflow/tfjs-vis"
import _ from "lodash"
import {toCamel} from"./../help/util"

//console.log(camelcaseKeys(JSON.parse(ModelTest)))
/*const lol = camelcaseKeys(ModelFou ,{deep:true})
console.log(JSON.parse(JSON.stringify(lol)))
const  json = JSON.parse(JSON.stringify(lol), (k, v) => v && typeof v === 'string' ? _.camelCase(v) : v);
console.log(json)*/




const SequentialLayers ={
    inputLayer:tf.layers.inputLayer,
    conv2D:tf.layers.conv2d,
    maxPooling2D:tf.layers.maxPool2d,
    dense:tf.layers.dense,
    flatten:tf.layers.flatten,
    dropout:tf.layers.dropout
}


export const summarize = (data:any,id:number) => {

    const model= tf.sequential();

    const loliz = toCamel(data);
  

      loliz.config.layers.map(function (item:any){
            model.add(SequentialLayers[item.className](item.config))
        })
 //model.summary();
    //const surface = { name: `Model :"${data.name}"  summary `, tab: `Model !(key:${id} )`};
    const surface = { name: `Model Summary id: ${id} `, tab: `${data.name} id: ${id}`};

    /*if(collapse)surface  = { name: `Model Summary of ${data.name} , id: ${id} `, tab: `Model`}
    else if (sigle) surface = { name: `Model Summary `, tab: `Model `}
    else surface = { name: `Model Summary id: ${id} `, tab: `${data.name} id: ${id}`}*/
    
    tfvis.show.modelSummary(surface, model);
    tfvis.visor().open();
       }


        
