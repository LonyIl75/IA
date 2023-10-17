export interface IGeneric<Class, Config> {
    class_name: Class;
    config: Config;
}



export interface Module {
    name: string;
}



export interface IInput {

    data_format: string; //data_format: typeof dataFormat[number];  TODO IMPORTANT #
}

export const dataFormat = [
    'channels_first',
    'channels_last'
]


export const padding = [
    "valid",
    "same"
]
export const dtype = [
    'float32',
    'float64',
    'mixed_float16',
    'mixed_bfloat16',

]



export interface IStrides extends IGeneric<string, [number, number]> { }

/*type InputShape ={
  class_name:string; //class_name:"__tuple__"
  items:[number|null,number,number,number];//[number,number] or [number,number,number,number] depend on class_name
}*/


/*type [number,number] ={
  class_name:string; //class_name:"__tuple__"
  items:[number,number];
}*/
/*interface IConfigSeed{
    seed:any;
}*/

