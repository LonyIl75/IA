import React,{useState} from 'react'

interface Props{
    path:string,
    setValue:Function,
    getValue:Function,
    defaultValue:string
}

export const ShapeInput:React.FC<Props> = ({path,setValue,getValue,defaultValue,...props}) => {

    /*let init_arr="  ";
    init_arr="1";if(size>1){
        if(size===4)init_arr="null"
        for(let i = 1, value = "1"; i < size; i++)init_arr=init_arr.concat(","+value);
    }
    init_arr="["+init_arr+"]"
    const [valeur,setValeur]=useState<string>(init_arr);*/
    const defaulte= getValue(`${path}`)?.map((value:number)=>value===null?"null":value.toString())
    return (
        <input /*value={valeur} onChange={(e)=>setValeur(e.target.value)}*/ onBlur={(e)=>setValue(`${path}`,e.target.value.replace(/, +/g, ",").split(",").map(Number))}  
        defaultValue={defaultValue}
        {...props}
    
        type="text"
        />

    )
}
