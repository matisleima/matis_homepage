const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8081, // Explicitly set frontend port
    proxy: 'http://localhost:8080'
 }
})
