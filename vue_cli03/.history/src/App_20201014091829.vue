<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  // 钩子函数
  created(){
    // get请求
    // axios.get('/posts').then(resp => {
    //   console.log(resp.data)
    // })

    // post请求
    axios.post("/posts").then(resp => {
      // 在axios中的then的异步操作传入的箭头函数能不能使用this指代vue的实例呢？可以的
      console.log(resp)
    })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
