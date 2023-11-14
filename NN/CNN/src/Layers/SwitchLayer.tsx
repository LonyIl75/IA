//@ts-nocheck
import React, { forwardRef, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import { useForm, Controller, useFieldArray,UseFormRegister } from "react-hook-form";
import { Checkbox ,MenuItem,Select} from "@material-ui/core";
import { getConstantValue } from "typescript";


//a changer 
import {Conv2D} from "./view/Conv2D"
import {Flatten} from "./view/Flatten"
import {Dense} from "./view/Dense"
import {MaxPooling2D} from "./view/MaxPooling2D"
import {Dropout} from "./view/Dropout"

//import {IModelConfig} from "../CreateLayers"//register error 2305 ts (source  : https://codesandbox.io/s/react-hook-form-typescript-xnb1u?file=/src/components/Input.tsx:163-176
import defaultLayers from "./Data/defaultLayers";
import {PropsSet} from "./Props/interfaceProps"


  console.log(defaultLayers)
interface SwitchProps extends PropsSet
    {
    unregister:Function;
    path:string;
    getValue:Function;
}


const handleChange =(e:React.ChangeEvent<HTMLInputElement>,setValue:Function)=>{
    setValue(e.target.value);
    console.log(e.target.value)
}

//type keyPair={key:string;layer:Object}
 function handleChanger(
    e: React.ChangeEvent<HTMLInputElement>,
    path: string,
    setValue:Function ,
    unregister:Function,
  ) {
    

    /*console.log("ici changer")
    console.log(`${path}`)
      console.log(e.target.value)
        console.log(typeof e.target.value);
        console.log(defaultValueL)
       console.log(defaultValueL[e.target.value])*/
   
        unregister(`${path}`);

       // setValue(`${path}`,{...defaultLayers[e.target.value]});
        //console.log(getValues(`${path}`))
      
  }


  const SwitchLayer:React.FC<SwitchProps> = ({unregister,getValue,register,setValue,path})=> {

     console.log("ici")
     const [selectValue,setSelect]=useState("Conv2D");
        const layers={ // a supprimer plus aucune utilité 
            Conv2D:Conv2D,
            MaxPooling2D:MaxPooling2D,
            Dense:Dense,
            Flatten:Flatten,
            Dropout:Dropout
        }
        

       const ClassName = layers[selectValue|| 'Conv2D'] ; //wrap dans une function et utiliser getValues 
       /* change for controller or consider watch to avoid rerendering root component*/
      return (
           <div>
       <select {...register(`${path}.class_name`)} value={selectValue}  onChange={
           (e:React.ChangeEvent<HTMLInputElement>)=>{setSelect(e.target.value);
           handleChanger(e,path,setValue,unregister);}
           
           
           } >
                        <option value={"Conv2D"}>Convolution 2D</option>
                        <option value={"MaxPooling2D"}>Max pooling 2D</option>
                        <option value={"Dense"}>Dense</option>
                        <option value={"Flatten"}>Flatten</option>
                        <option value={"Dropout"}>Dropout</option>
        </select>
       
       
       <ClassName register={register} getValue={getValue} path={`${path}`} setValue={setValue}/>

        </div>
           
           
           );
    }
    export default SwitchLayer ;


    //rendre switchlayer reutilisable : select viens de  data
    
    