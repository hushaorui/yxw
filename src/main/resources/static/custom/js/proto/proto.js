/*Websocket沟通协议相关*/

/*响应类型*/
const resType = "resType";
/*响应体携带的真正json数据或字符串*/
const message = "message";

const protoMap = new Map();

function registerWSHandler(baseProtoName, subProtoName, callback) {
    let currentProto = protoMap.get(baseProtoName);
    if (currentProto == null) {
        currentProto = new Map();
        protoMap.put(baseProtoName, currentProto);
    }
    console.log("成功添加，基础协议：" + baseProtoName + "，子协议：" + subProtoName);
    currentProto.put(subProtoName, callback);
}

// 处理服务端返回的响应
function handleResponse(ws, event) {
    // 字符串转json
    let baseProtoData = JSON.parse(event.data);
    // 基础协议的类型，参考 WsProtoConstants
    let baseProtoType = baseProtoData["baseType"];
    let subProtoMap = protoMap.get(baseProtoType);
    if (subProtoMap == null) {
        console.log(baseProtoData);
        alert("未知的基础协议类型：" + baseProtoType);
    } else {
        // 子协议内容
        let subProtoData = JSON.parse(baseProtoData["protoString"]);
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

// 公用发送请求
function sendRequest(ws, baseProtoType, protoType, messageJson) {
    if (! hasConnected) {
        return;
    }
    // 最后发送的结构
    let jsonData = {baseType : baseProtoType, "protoString" : JSON.stringify(messageJson)};
    // 发送
    ws.send(JSON.stringify(jsonData));
}
