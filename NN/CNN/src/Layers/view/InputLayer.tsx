import React, { forwardRef, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import { useForm, Controller, useFieldArray } from "react-hook-form";
import { Checkbox ,MenuItem,Select} from "@material-ui/core";
import { getConstantValue } from "typescript";
import {PropsLayer} from "../Props/interfaceProps"
import {ShapeInput} from "./../../help/ShapeInput"
import defaultLayers from "./../../Layers/Data/defaultLayers"
import { setLocale } from "yup";
export  const InputLayer:React.FC<PropsLayer> = ({register,path,getValue,setValue}) => {

  const [lrl,setLrl]=useState(false)
useEffect(() => {
 setValue(`${path}`,defaultLayers["InputLayer"])
 setLrl(true)
},[])

 return (
         <div className="card">
                <h5 className="card-header">Input Layer</h5>
                  <div className="card-body border-bottom">
                    <div className="form-row">


                      <div className="form-group col-md-2">
                           <label >Batch_input_shape:</label>
                            <ShapeInput setValue={setValue} getValue={getValue} path={`${path}.config.batch_input_shape`} /*pattern="[1-5][0-9]{0,2}" */ defaultValue={"null,28,28,3"}required />
                        </div>

                        <div className="form-group col-md-2">
                           <label >dtype:</label>
                             <select {...register(`${path}.config.dtype`)} >
                             <option value="float32" >float32</option>
                             <option value="float64"  >float64</option>
                             <option value="mixed_float16"  >mixed_float16</option>
                             <option value="mixed_bfloat16" >mixed_bfloat16</option>
                           </select>
                           </div>

                           <div className="form-group col-md-2">

                           <label >Name</label>
                           <input type ="text" {...register(`${path}.name`)} />
                        </div>

                    </div>
                  </div>
                  </div>
    )
}