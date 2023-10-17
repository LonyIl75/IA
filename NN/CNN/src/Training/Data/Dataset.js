
export default {
   MNIST:{
            name:"MNIST",
            versions:"3.0.1",
            size:11.06,
            FeaturesDict:{
                    image:{
                    type:"Image",
                    shape:[28, 28, 1],
                    dtype:"tf.uint8"
                },
                classLabel:{
                    shape:[],
                    dtype:"tf.int64",
                    num_classes:10

            
        }
    }

        }
        ,
        FashionMNIST:{
            name: "Fashion-MNIST",
            versions:"3.0.1",
            size:29.45,
             FeaturesDict:{
                image:{
                    type:"Image",
                    shape:[28, 28, 1],
                    dtype:"tf.uint8"
                },
                label:{
                    type:"ClassLabel",
                    shape:[],
                    dtype:"tf.int64",
                    num_classes:10

                }  
        
    } 

        },
        KMNIST:{
            name:"kmist",
            versions:"3.0.1",
            size:20.26,
            FeaturesDict:{
                image:{
                    type:"Image",
                    shape:[28, 28, 1],
                    dtype:"tf.uint8"
                },
                label:{
                    type:"ClassLabel",
                    shape:[],
                    dtype:"tf.int64",
                    num_classes:10

                }  
            
        } 

        },
        CIFAR:{
            name:"cifar",
            versions:"3.0.2",
            size:162.67,
            FeaturesDict:{
                id:{
                    type:"Text",
                    shape:[],
                    dtype:"tf.string"
                },
                image:{
                    type:"Image",
                    shape:[32, 32, 3],
                    dtype:"tf.uint8"
                },
                label: {
                    type:"ClassLabel",
                    shape:[],
                    dtype:"tf.int64",
                    num_classes:10
            }
        }
        }
            
            
}
        
    

