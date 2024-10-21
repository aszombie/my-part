import { createPinia } from 'pinia'
import  piniaPluginpersostedstate  from 'pinia-plugin-persistedstate'

const pinia=createPinia().use(piniaPluginpersostedstate);

export default pinia;
