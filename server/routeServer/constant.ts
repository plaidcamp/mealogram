import fs from 'fs'
import ini from 'ini'

class constant {
    // class properties
    file: string
    config: ini

    // constructor
    constructor(f:string = 'master') {
        this.file = f 
        this.config = ini.parse(fs.readFileSync(`./configs/${f}.ini`, 'utf-8'))
    }

    getData(key, value) {
        var config = this.config

        if(value == undefined) { return config[key] }
        else { return config[key][value] }
    }
}

export default new constant()