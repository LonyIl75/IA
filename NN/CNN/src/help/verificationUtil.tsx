import * as help from './IntefaceUtil'
import * as Yup from "yup"

//UTIL:

export const tuple = (length: number, _type: string) => {

    return Yup.array().of(Yup.mixed()).required("tuple is required").test({
        message: "${path} " + ` must have ${_type} type`,
        test: (arr: any) => {
            for (let i = 0; i < length; i++) {
                console.log(arr[i])
                if (!(typeof arr[i] === _type)) return false;
            }
            return true
        }
    }).transform((arr: any, original_arr: any) => {
        console.log(arr)
        let bool = true;
        for (let i = 0.; bool && i < arr.length; i++) {
            if (typeof arr[i] !== "number" && arr[i] !== null) bool = false
        }
        if (bool) {
            return length % 2 === 0 && arr.length === length / 2 ? arr.reduce(function (res: any[], current: any, index: any, array: any[]) {
                return res.concat([current, current]);
            }, []) : arr
        }
        else {
            throw new Yup.ValidationError("${path} " + ` must have ${_type} type`)
        }

    }).test({
        message: "${path} " + `must be ${length}`,
        test: (arr: any) => { return arr.length === length }
    })
}

export const tuple2 = () => {
    return tuple(2, "number")
}



export const inputShape = () => {
    return Yup.array().of(Yup.mixed()).required("input shape is required").test({
        message: "${path} incorrect type ",
        test: (arr: any) => {
            for (let i = 0; i < 4; i++) {
                if (!((typeof arr[i] === "number" || (arr[i] === null && i === 0)))) return false;
            }
            return true
        }
    })
        .test({
            message: "${path} must be 4 length",
            test: (arr: any) => { return arr.length === 4 }
        })
}



//UTIL INTERFACE

export const generic = (value: any) => {
    return Yup.object({
        class_name: Yup.mixed().oneOf(Object.keys(value.schema_collection)),
        config: value.schema_collection[value.class_name],

    })
}

export const Module = Yup.object({ name: Yup.string().default("unknow") })

export const Input = Yup.object({
    data_format: Yup.mixed().oneOf(help.dataFormat).default("channels_first")
})


export const Strides = Yup.object({
    strides: tuple2()
})

export const Layer = Yup.object({
    trainable: Yup.boolean().default(true),
    dtype: Yup.mixed().oneOf(help.dtype).default("float32"),
    dynamic: Yup.boolean().default(false),
}).concat(Module)

