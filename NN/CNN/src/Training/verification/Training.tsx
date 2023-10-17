import * as Yup from "yup"
import * as help from "./../../help/IntefaceUtil"
import { lossFunction, classLoss } from "./../Interface/Metrics"
import * as help_verif from "./../../help/verificationUtil"

//OPTIMIZER:

export const ConfigOptimizer = Yup.object({
    learning_rate: Yup.number().default(0.001),
}).concat(help_verif.Module)





const xAdam = Yup.object({
    beta_1: Yup.number().default(0.9).positive().strict(),
    beta_2: Yup.number().default(0.999).strict(),
    epsilon: Yup.number().default(1e-07),
}).concat(ConfigOptimizer)








const ConfigAdamax = Yup.object({
    beta_1: Yup.number().default(0.9).positive().strict(),
    beta_2: Yup.number().default(0.999).strict(),
    epsilon: Yup.number().default(1e-07),
}).concat(ConfigOptimizer)
const ConfigNadam = Yup.object({}).concat(xAdam)
const ConfigAdam = Yup.object({ amsgrad: Yup.boolean().default(false) }).concat(xAdam)

const ConfigAdadelta = Yup.object({
    rho: Yup.number().default(0.95),
    epsilon: Yup.number().default(1e-07),

}).concat(ConfigOptimizer)

const ConfigAdagrad = Yup.object({
    initial_accumulator_value: Yup.number().default(0.1),
    epsilon: Yup.number().default(1e-07),

}).concat(ConfigOptimizer)


const ConfigMomemtum = Yup.object({ momentum: Yup.number().default(0.0), }).concat(ConfigOptimizer)

const ConfigRMSprop = Yup.object({
    rho: Yup.number().default(0.9),
    epsilon: Yup.number().default(1e-07),
    centered: Yup.boolean().default(false)
}).concat(ConfigMomemtum)

const ConfigSGD = Yup.object({ nesterov: Yup.boolean().default(false) }).concat(ConfigOptimizer)

const ConfigFtrl = Yup.object({

    learning_rate_power: Yup.number().default(-0.5).test({
        message: "${path} must be <=0  ",
        test: (val: any) => { return val <= 0 }
    }),
    initial_accumulator_value: Yup.number().default(0.1).test({
        message: "${path} must be >=0  ",
        test: (val: any) => { return val >= 0 }
    }),
    l1_regularization_strength: Yup.number().default(0.1).test({
        message: "${path} must be >=0  ",
        test: (val: any) => { return val >= 0 }
    }),
    l2_regularization_strength: Yup.number().default(0.1).test({
        message: "${path} must be >=0  ",
        test: (val: any) => { return val >= 0 }
    }),
    l2_shrinkage_regularization_strength: Yup.number().default(0.1).test({
        message: "${path} must be >=0  ",
        test: (val: any) => { return val >= 0 }
    }),
    beta: Yup.number().default(0.0),
}).concat(ConfigOptimizer)


export const SchemasOptimizers = {

    Adadelta: ConfigAdadelta,
    Adagrad: ConfigAdagrad,
    Adam: ConfigAdam,
    Adamax: ConfigAdamax,
    Ftrl: ConfigFtrl,
    Nadam: ConfigNadam,
    RMSprop: ConfigRMSprop,
    SGD: ConfigSGD,
}



const Adadelta = Yup.lazy((value: any): any => { return help_verif.generic({ schema_collection: SchemasOptimizers, ...value["Adadelta"] }) })

const Adagrad = Yup.lazy((value: any): any => { return help_verif.generic({ schema_collection: SchemasOptimizers, class_name: "Adagrad", ...value }) })

const Adam = Yup.lazy((value: any): any => { return help_verif.generic({ schema_collection: SchemasOptimizers, class_name: "Adam", ...value }) })

const Adamax = Yup.lazy((value: any): any => { return help_verif.generic({ schema_collection: SchemasOptimizers, class_name: "Adamax", ...value }) })

const Ftrl = Yup.lazy((value: any): any => { return help_verif.generic({ schema_collection: SchemasOptimizers, class_name: "Ftrl", ...value }) })

const Nadam = Yup.lazy((value: any): any => { return help_verif.generic({ schema_collection: SchemasOptimizers, class_name: "Nadam", ...value }) })

const RMSprop = Yup.lazy((value: any): any => { return help_verif.generic({ schema_collection: SchemasOptimizers, class_name: "RMSprop", ...value }) })

const SGD = Yup.lazy((value: any): any => { return help_verif.generic({ schema_collection: SchemasOptimizers, class_name: "SGD", ...value }) })













//TRAININGCONFIG:


//OUBLIE:
enum Metrics {

}

const Truc = Yup.object({
    dtype: Yup.mixed().oneOf(help.padding),
    fn: Yup.mixed().oneOf(lossFunction),
}).concat(help_verif.Module)



const SchemasMetrics = {

    AUC: Yup.object({}),

    Accuracy: Yup.object({}),

    BinaryAccuracy: Yup.object({}),

    BinaryCrossentropy: Yup.object({}),

    CategoricalAccuracy: Yup.object({}),

    CategoricalCrossentropy: Yup.object({}),

    CategoricalHinge: Yup.object({}),

    CosineSimilarity: Yup.object({}),

    FalseNegatives: Yup.object({}),

    FalsePositives: Yup.object({}),

    Hinge: Yup.object({}),

    KLDivergence: Yup.object({}),

    LogCoshError: Yup.object({}),

    Mean: Yup.object({}),

    MeanAbsoluteError: Yup.object({}),

    MeanAbsolutePercentageError: Yup.object({}),

    MeanIoU: Yup.object({}),

    MeanRelativeError: Yup.object({}),

    MeanSquaredError: Yup.object({}),

    MeanSquaredLogarithmicError: Yup.object({}),

    MeanTensor: Yup.object({}),

    Metric: Yup.object({}),

    Poisson: Yup.object({}),

    Precision: Yup.object({}),

    PrecisionAtRecall: Yup.object({}),

    Recall: Yup.object({}),

    RecallAtPrecision: Yup.object({}),

    RootMeanSquaredError: Yup.object({}),

    SensitivityAtSpecificity: Yup.object({}),

    SparseCategoricalAccuracy: Yup.object({}),

    SparseCategoricalCrossentropy: Yup.object({}),

    SparseTopKCategoricalAccuracy: Yup.object({}),

    SpecificityAtSensitivity: Yup.object({}),

    SquaredHinge: Yup.object({}),

    Sum: Yup.object({}),

    TopKCategoricalAccuracy: Yup.object({}),

    TrueNegatives: Yup.object({}),

    TruePositives: Yup.object({}),



}

//INPUTHEADER:
const SchemasDataset = {
    MNIST: Yup.object({}),
    CIFAR: Yup.object({}),
    KMNIST: Yup.object({}),
    FashionMNIST: Yup.object({}),

}
const CustomParam = Yup.object({
    batch_size: Yup.number().integer().default(1).test({ message: "batch size doit etre strictement positif", test: (val: any) => { return val > 0 } }),
    epoch: Yup.number().integer().default(1).test({ message: "epoch doit etre strictement positif", test: (val: any) => { return val > 0 } }),
      
})
const InputHeader = Yup.lazy((value: any) => {
    return Yup.object({
        custom_param:CustomParam,
         //layers:Yup.array().of(Yup.mixed().oneOf(Object.keys(SchemasLayers))),
        dataset_config: Yup.object({})//help_verif.generic({schema_collection:SchemasDataset,...value["dataset_config"]}).required().default({class_name:Yup.mixed().default("MNIST"),config:SchemasDataset["MNIST"]}),
    })
}
)

const ConfigDataset =Yup.object({

    name: Yup.string(),
    versions: Yup.string(),
    size: Yup.number(),
    featuresDict: Yup.mixed()
})

//TRAINING :

export const TrainingConfig = Yup.lazy((value: any) => {

    return Yup.object().shape({
        loss: Yup.mixed().oneOf([...classLoss, ...lossFunction]),
        weighted_metrics: Yup.object({}).nullable().default(null),
        loss_weights: Yup.object({}).nullable().default(null),
        optimizer_config: help_verif.generic({ schema_collection: SchemasOptimizers, ...value["optimizer_config"] }).required().default({ class_name: Yup.mixed().default("rmsprop"), config: SchemasOptimizers["rmsprop"] }),
        metrics: Yup.array().of(Yup.array().of(Yup.object().shape({
            class_name: Yup.string().required(),
            config: Yup.mixed()
        })).test(
            {
                message: "ca  marche ?",
                test: (arr_metrics: any) => {

                    for (let i = 0; i < arr_metrics.length; i++) {

                        if (!Object.keys(SchemasMetrics).includes(arr_metrics[i].class_name)
                            || !SchemasMetrics[arr_metrics[i].class_name].isValidSync(arr_metrics[i].config)) return false;
                    }
                    return true;
                }
            })),
        input_header: InputHeader

    })
})

