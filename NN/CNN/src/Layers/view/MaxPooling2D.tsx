import React, { forwardRef, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import { useForm, Controller, useFieldArray } from "react-hook-form";
import { Checkbox ,MenuItem,Select} from "@material-ui/core";
import { getConstantValue } from "typescript";
import {PropsLayer} from "../Props/interfaceProps"
import defaultLayers from "./../Data/defaultLayers"
import {ShapeInput} from "./../../help/ShapeInput"




export  const MaxPooling2D:React.FC<PropsLayer> = ({register,getValue,path,setValue}) => {
    console.log("max")
    console.log(path)
    console.log(defaultLayers["MaxPooling2D"])

    setValue(`${path}`,defaultLayers["MaxPooling2D"])
    return (
           <div className="card">
                <h5 className="card-header">MaxPool</h5>
                  <div className="card-body border-bottom">
                    <div className="form-row">
                      <div className="form-group col-md-2">
                           <label >Pool_size:</label>
                           <ShapeInput setValue={setValue} getValue={getValue} path={`${path}.config.pool_size`} defaultValue={"2,2"} />
                        </div>
                        <div  className="form-group col-md-2">
                           <label >Strides:</label>
                          <ShapeInput setValue={setValue} getValue={getValue} path={`${path}.config.strides`} defaultValue={"1,1"}  /*pattern="\((\d),\1+\)"*/ />
                           </div>
                           <div className="form-group col-md-2">
                            <label>Padding:</label>
                             <select {...register(`${path}.config.padding`)}  >
                           <option value="valid"  >valid</option>
                             <option value="same"  >same</option>
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
