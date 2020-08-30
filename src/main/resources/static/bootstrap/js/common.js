
// 将时间戳转换为可读字符串 （公共方法）
function timestampToDate(timestamp) {
    if ((typeof timestamp)  == "string") {
        timestamp = parseInt(timestamp.trim());
    }
    let date = new Date(timestamp);
    return dateToString(date);
}
// 将时间对象转换为可读字符串
function dateToString(date) {
    let y = date.getFullYear();
    let m = date.getMonth()+1;
    let d = date.getDate();
    let h = date.getHours();
    let mm = date.getMinutes();
    let s = date.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}
function add0(m){return m<10?'0'+m:m }


Array.prototype.remove = function(s) {
    for (let i = 0; i < this.length; i++) {
        if (s === this[i])
            this.splice(i, 1);
    }
}

function Map() {
    /** 存放键的数组(遍历用到) */
    this.keys = [];
    /** 存放数据 */
    this.data = {};
    /**
     * 放入一个键值对
     * @param {String} key
     * @param {Object} value
     */
    this.put = function(key, value) {
        if(this.data[key] == null){
            this.keys.push(key);
        }
        this.data[key] = value;
    };
    /**
     * 获取某键对应的值
     * @param {String} key
     * @return {Object} value
     */
    this.get = function(key) {
        return this.data[key];
    };
    /**
     * 删除一个键值对
     * @param {String} key
     */
    this.remove = function(key) {
        this.keys.remove(key);
        this.data[key] = null;
    };
    /**
     * 遍历Map,执行处理函数
     *
     * @param fn {Function} 回调函数 function(key,value,index){..}
     */
    this.each = function(fn){
        if(typeof fn != 'function'){
            return;
        }
        let len = this.keys.length;
        for(let i = 0; i < len; i++){
            let k = this.keys[i];
            fn(k,this.data[k],i);
        }
    };

    /**
     * 获取键值数组(类似Java的entrySet())
     * @return any[]{key,value}的数组
     */
    this.entrys = function() {
        let len = this.keys.length;
        let entrys = new Array(len);
        for (let i = 0; i < len; i++) {
            entrys[i] = {
                key : this.keys[i],
                value : this.data[i]
            };
        }
        return entrys;
    };
    /**
     * 判断Map是否为空
     */
    this.isEmpty = function() {
        return this.keys.length === 0;
    };
    /**
     * 获取键值对数量
     */
    this.size = function(){
        return this.keys.length;
    };
    /**
     * 重写toString
     */
    this.toString = function(){
        let s = "{";
        for(let i=0;i<this.keys.length;i++,s+=','){
            let k = this.keys[i];
            s += k+"="+this.data[k];
        }
        s+="}";
        return s;
    };
}