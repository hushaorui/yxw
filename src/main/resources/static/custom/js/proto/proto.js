/*Websocket沟通协议相关*/

/*基础协议类型*/
const baseType = "baseType";
/*请求类型*/
const reqType = "reqType";
/*基础协议内的子协议字符串*/
const protoString = "protoString";
/*响应类型*/
const resType = "resType";
/*响应体携带的真正json数据或字符串*/
const message = "message";

const protoMap = new Map();
{
    // 所有的基础协议都需要在这里添加，请参照类：BaseProtoType.java，否则无法注册
    protoMap.put("BASE_HEART_BEAT", new Map());
    protoMap.put("BASE_CHAT_HALL", new Map());

    // 最后打印所有
    console.log("基础协议：" + protoMap.toString());
}
function registerWSHandler(baseProtoName, subProtoName, callback) {
    let currentProto = protoMap.get(baseProtoName);
    if (currentProto == null) {
        console.log("处理类注册失败！未知的基础协议名称：" + baseProtoName);
    } else {
        currentProto.put(subProtoName, callback);
    }
}

// 处理服务端返回的响应
function handleResponse(ws, event) {
    // 字符串转json
    let baseProtoData = JSON.parse(event.data);
    // 基础协议的类型，参考 WsProtoConstants
    let baseProtoType = baseProtoData[baseType];
    let subProtoMap = protoMap.get(baseProtoType);
    if (subProtoMap == null) {
        alert("未知的基础协议类型：" + event.data["baseType"]);
    } else {
        // 子协议内容
        let subProtoData = JSON.parse(baseProtoData[protoString]);
        // 子协议类型
        let subProtoType = subProtoData[resType];
        let callback = subProtoMap.get(subProtoType);
        if (callback == null) {
            alert("基础协议名：" + baseProtoType + "，未知的子协议名称：" + subProtoType);
        } else {
            // 回调
            callback(subProtoData);
        }
    }
}
