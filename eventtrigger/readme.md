

para pode capturar eventos de un s3 se usa la clase S3Event 
porque como tal el evento trae lo siguiente

// S3Event creeria que seria la forma que abarcaria multiples eventos o objetos?
// debe de haber uno que solo capture un solo evento 

 getBucket{Records=[
        {eventVersion=2.1, eventSource=aws:s3, awsRegion=us-east-2, eventTime=2023-11-09T19: 01: 45.132Z, eventName=ObjectCreated:Put, userIdentity={principalId=AWS:AROAXFWWTRRWGV3NB36TP:LambditaSubirArchivosJdorado
            }, requestParameters={sourceIPAddress=3.145.48.139
            }, responseElements={x-amz-request-id=GZ4SSKHZTKH122NY, x-amz-id-2=Y5PS/pmiwhu3LljaP5mVc5AmO2o04o9XE71BWC7jpOdaiHupOGTmq0dWNJpE1hVp/BZxzJ83jKzoMS+bxKphn5FW0h6u5Vdx
            }, s3={s3SchemaVersion=1.0, configurationId=b75786ee-fe2d-4473-bccd-570f6bce93e1, bucket={name=lambda-functions-java-jdorado, ownerIdentity={principalId=A287JR0UTUB1FA
                    }, arn=arn:aws:s3: : :lambda-functions-java-jdorado
                }, object={key=imgaenesHot/2023-11-09_19-01-44_preubas-qa.jpg, size=10944, eTag=efa3051e99b4d6d4fbe55009c8ac8224, sequencer=00654D2C990FA8482B
                }
            }
        }
    ]
}
cada cosita significa lo siguiente: 

eventVersion: La versión del formato del evento. En este caso, es la versión 2.1.

eventSource: Indica la fuente del evento. En este caso, "aws:s3" significa que el evento proviene de S3.

awsRegion: La región de AWS en la que se originó el evento. En este caso, "us-east-2".

eventTime: La marca de tiempo en la que ocurrió el evento, en formato UTC.

eventName: El tipo de evento que ocurrió. "ObjectCreated:Put" indica que un nuevo objeto fue creado en el bucket debido a una operación de carga (Put).

userIdentity: Información sobre la identidad del usuario que realizó la acción. En este caso, se proporciona el "principalId", que parece ser un identificador único relacionado con el usuario o la entidad que subió el archivo.

requestParameters: Detalles sobre la solicitud que desencadenó el evento. Aquí, se incluye la "sourceIPAddress", que indica la dirección IP desde la cual se originó la solicitud.

responseElements: Información sobre la respuesta a la solicitud. Incluye identificadores relacionados con la solicitud, como "x-amz-request-id" y "x-amz-id-2".

s3: Contiene información específica sobre el evento de S3.

s3SchemaVersion: La versión del esquema del evento de S3. En este caso, "1.0".

configurationId: Un identificador único para la configuración del evento.

bucket: Detalles sobre el cubo de S3 en el que ocurrió el evento.

name: El nombre del cubo, en este caso, "lambda-functions-java-jdorado".

ownerIdentity: Información sobre la identidad del propietario del cubo.

arn: El Amazon Resource Name (ARN) del cubo.

object: Detalles sobre el objeto que se creó en el cubo.

key: La clave o ruta del objeto dentro del cubo, en este caso, "imgaenesHot/2023-11-09_19-01-44_preubas-qa.jpg".

size: El tamaño del objeto en bytes.

eTag: La etiqueta de entidad (ETag) del objeto.

sequencer: Un identificador único que indica el orden de las operaciones en el objeto.