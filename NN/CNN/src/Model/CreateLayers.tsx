  
import React, { forwardRef,useState ,useEffect} from "react";
import ReactDOM from "react-dom"
import * as tfvis from "@tensorflow/tfjs-vis"
//import { yupResolver } from "@hookform/resolvers";
import { makeStyles } from "@material-ui/core/styles";
import { useHistory } from "react-router-dom";
import {useForm,Controller,useFieldArray,FieldError } from "react-hook-form"
import { Input, Select, MenuItem,Checkbox,TextField } from "@material-ui/core";
import SwitchLayer from "./../Layers/SwitchLayer"
import {InputLayer} from "./../Layers/view/InputLayer"
import{summarize} from "./SequentialModel"
import { useModels } from "./../../ModelsContext";
import defaultLayers from "./../Layers/Data/defaultLayers"
import defaultM from "./../Model/Data/defaultModel"
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from "yup";
import {fake_await} from "./../help/utildebug"
import {Model} from "./../Model/verification/Model"
import db from"./../Provider/db"
import{IModel} from "./../Model/Interface/Model"

const useStyles = makeStyles(()=>({
    root: {
    width: "100%",
  },

}));


const Form:React.FC = ({children,...props})=>{

    const styles = useStyles()
    return (

        <form className="model-form" noValidate {...props} > {/* no validate =suppr default ,  Yup prend relais */}
            {children}
        </form>
    )
}




export const CreateLayers = () => {
    const [selectValue,setSelect]=useState("Conv2D");
    const history = useHistory();
     const { appendModel } = useModels();
     const [checked, setChecked] = React.useState(true);

     const { register, handleSubmit, setValue, reset,unregister,getValues,clearErrors,setError,  formState:{ errors },control } = useForm<IModel>(
     { 
       //resolver: yupResolver(Model),   
      mode: 'onSubmit',
      defaultValues:defaultM
    });

        const { fields, append, prepend, remove, swap, move, insert } = useFieldArray({
    control,
    name: "config.layers"
  });


   const appendLayer = () => {append(defaultLayers["Conv2D"]);};


  const onSubmit = (data:any) => {

        console.log("validation Model : ")
        console.log(data)
        
        console.log(Model.validateSync(data))
        let myn_model={
          body:data, //nb_max : model = 25 TODO 
        }
        console.log("OH BOY")

      db.table('waitingModels').add({...myn_model}).then((id)=>{

          fake_await(10000).then(()=>{
          //useModels : tableau de promesse soit en race soit en FIFO ou LIFO si race alors find by id mais pas elegant trouver mieux si FIFO ou LIFA first last find 
          db.table('Models2').add({...myn_model}).then((id)=>{
            db.table('Models2').get(id).then((value)=>{alert(`Le modele ${value.body.name} a été appris `)})})

          }).then(()=>{
       
          db.table('waitingModels')
          .delete(id)
        })

           //devrait return une promesse et un blob
          summarize(data,id)
        
            /*  const res = summarize(data);
          res.blob().then((blob)=>db.table('summaries').put({
            id:id,
            image: blob
            */

          //vue que pas de blob fenetere modale
          setTimeout(()=>{tfvis.visor().toggle();history.push("./Repertoire")},8000) // A CHANGER POUR LE SERV

      })
       

      };

       //il faudrait le chainer avec le premier appel
          //appel serv 
             
         /* fake_await(10000,id).then((id)=>{
            console.log("fheuh")
            db.table('waitingModels').get(id).then(console.log)
          
          //useModels : tableau de promesse soit en race soit en FIFO ou LIFO si race alors find by id mais pas elegant trouver mieux si FIFO ou LIFA first last find 
          db.table('Models2').add({...myn_model}).then((id)=>{
          db.table('waitingModels')
          .delete(id)})})*/
 
    return (
        <div>
        <Form onSubmit={handleSubmit(onSubmit)}>
             <div className="model-form">
                 <h2>Model Name:</h2>
                  <input type="text" {...register("name")} />

                </div>
                <div className="model-form">
                    <h2>Layers:</h2>

                    <h3>Others:</h3>
                     <ul>
                        { fields.map((item, index) => {
                          
                        if(index==0){
                        return(
                        <>
                        <li key={item.id}>
                        <h3>Input:</h3>
                        <InputLayer register={register} getValue={getValues} path={`config.layers.${index}`} setValue={setValue}/>
                        </li>
                        <h3>Intermediate Layer:</h3>
                        </>
                        )
                        }else{
                        return (
                        <li key={item.id}>
                    
                        <SwitchLayer  getValue={getValues} path={`config.layers.${index}`} unregister={unregister} register={register}  setValue={setValue} />
                        <button onClick={() => remove(index)}>rm</button> 
                    </li>
                    
                        )}})}
                        
                    </ul>
                    <button
                    type="button"
                    onClick={appendLayer}
                    >
                    Append
                    </button>
                    <div>
                     <h3>Training Option :</h3>
                     {/*<Training register={register} setValue={setValue}/>*/}

                     </div>

                </div>
      
      <input type="submit" />
    </Form>
    </div>
  );
    }
