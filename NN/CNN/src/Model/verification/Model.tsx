import * as help from "./../../help/IntefaceUtil"
import * as help_verif from "../../help/verificationUtil"
import * as Yup from "yup"
import {Models} from "./../Interface/Model"
import {SchemasLayers,ConfigLayer} from "./../../Layers/verification/Layers"
import  {TrainingConfig}  from "./../../Training/verification/Training"
import db from"./../../Provider/db"


//MODEL:

export const ConfigModel = Yup.lazy((value: any) => {
    return Yup.object({
        layers: Yup.array().of(
            Yup.object().shape({
                class_name: Yup.string().required(),//test one of unionclassname
                config: ConfigLayer
            }).nullable()).test({
                test: (arr_layers: any) => {
                    for (let i = 0; i < arr_layers.length; i++) {
                        if (!Object.keys(SchemasLayers).includes(arr_layers[i].class_name)
                            || !SchemasLayers[arr_layers[i].class_name].isValidSync(arr_layers[i].config)
                        )
                            return false;
                    }
                    return true;
                }
            })
    })
})


const SchemasModel = {
    Sequential: ConfigModel,
    Functional: Yup.object({ ler: Yup.number().strict().default(47) })
}

export const Model = Yup.lazy((value: any) => {

    return Yup.object({
        name:Yup.string()/*.test({
            message:'Model Name must be unique ',
                test: async function (name_model: any){
            //return db.table('Model').where("body.name").equals(name_model).count( function (count){ return count>0})
            return db.table('Models2').filter(function (model:any) {console.log(name_model+"=");console.log(model.body.name);return model.body.name === name_model;}).count().then((res)=>{return res==0})&&
            db.table('waitingModels').filter(function (model:any) {console.log(name_model+"=");console.log(model.body.name);return model.body.name === name_model;}).count().then((res)=>{return res==0})
         
        }})*/,
        expects_training_arg: Yup.boolean().default(true),
        batch_input_shape: help_verif.inputShape(),
        must_restore_from_config: Yup.boolean().default(true),
        class_name: Yup.mixed().oneOf(Models),
        config: SchemasModel[value.class_name],
        training_config: TrainingConfig
    })

})

