export interface BaseProps{
      register:Function; 
   
}
export interface PropsSet extends BaseProps{
    setValue:Function;
}
export interface PropsLayer extends PropsSet{
    path:string;
    getValue:Function;
}
