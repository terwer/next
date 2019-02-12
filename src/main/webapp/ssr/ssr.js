const path = require("path");

const { createBundleRenderer } = require("vue-server-renderer");

const serverBundle = path.resolve("../.nuxt/dist/server/server.manifest.json");
console.log(serverBundle);
// const clientManifest = "../.nuxt/dist/server/client-manifest.json";
const renderer = createBundleRenderer(serverBundle, {
  runInNewContext: false // 推荐
  // template, // （可选）页面模板
  // clientManifest // （可选）客户端构建 manifest
});

console.log(renderer);

// 在服务器处理函数中……
// server.get('*', (req, res) => {
// const context = { url: req.url };
const context = { url: "/" };
//     // 这里无需传入一个应用程序，因为在执行 bundle 时已经自动创建过。
//     // 现在我们的服务器与应用程序已经解耦！
renderer.renderToString(context, (err, html) => {
  if (err) {
    console.log(err);
  }
  console.log(html);
  // 处理异常……
  // res.end(html)
});
// })
