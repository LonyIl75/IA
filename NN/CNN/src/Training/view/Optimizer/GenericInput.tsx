import React ,{useEffect,useState}from 'react'



export interface PropsLayer {
    register:Function,
    path:string,
}

interface GenericProp extends PropsLayer{
pathDefaultM:string,
classe:string
}
interface InputProp extends PropsLayer
    {type:string,label:string,val:any}


    // Generic INPUT form Optimizer and EPoch/batch 
const Input:React.FC<InputProp> =({type,label,val,register,path},props:any)=>{
  if(type==="number"){//SALE TODO
     return (<div>
      <label>{label}</label>
     <input type={type} defaultValue={val} {...register(`${path}.${label}`)} {...props} step="any"/>
     </div>)}
     else{
    return(
        <div>
            <label>{label}</label>
            <input type={type} defaultValue={val} {...register(`${path}.${label}`)} {...props} />
        </div>


    )
} 
}



async function Marche(classe:any,pathDefaultM:any,path:any,register:any,setUser:Function){


//import default Module for select

  let defaultModule= await import(`${pathDefaultM}`);

      const Activity ={...defaultModule.default[classe]}
      type Activity = typeof Activity;

    return(
        <>
           {Object.keys(Activity).map((key,index) => {

//generic
   switch(typeof Activity[key] ){
       case 'number':
            return (
                <Input type="number" label={key} key={index} val={Activity[key]} path={path} register={register}   />);
        case 'string':
            return (<Input type="text" label={key}  key={index}  val={Activity[key]} path={path} register={register}/>);
        case 'object':
            return (<select key={index} >
                <option value="attends">attends</option>
                </select>
                );
        default:
            return(<div key={index} ></div>);
   }})}
   </>)

}
            

export const GenericInput:React.FC<GenericProp>= ({classe,register,path,pathDefaultM}) => {

   
  const [generic, setGeneric] = useState(null);

  useEffect(() => {
     Marche(classe,pathDefaultM,path,register,setGeneric).then((u:any) => setGeneric(u));
   
  }, []);
  
//wait for import
  if (generic === null) {
    return <div>Ecris des any ...</div>;
  }
  return (
    <div>
      {generic}
    </div>
  );
}


