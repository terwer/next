<template>
  <el-row>
    <el-col :xs="24" :md="5" :xl="3">
      <div class="img-wrap">
        <nuxt-link to="/" class="float-left">
          <div class="img-align-middle">
            <img
              class="logo"
              src="../../../assets/logo-dark.png"
              alt="jvue"
              title="jvue"
            />
          </div>
        </nuxt-link>
      </div>
    </el-col>
    <el-col :xs="0" :md="13" :xl="15">
      <div class="grid-content bg-purple-light">
        <HeaderMenu />
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" :md="6">
      <div class="search s-dark">
        <el-input
          v-model="s"
          placeholder="请输入关键字"
          clearable
          @keyup.enter.native="doSearch"
        >
          <el-button
            slot="append"
            class="s-dark-btn"
            type="primary"
            icon="el-icon-search"
            @click="doSearch"
          >
            搜索
          </el-button>
        </el-input>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { getLogger } from "../../../util/logger";
import HeaderMenu from "./HeaderMenu";
const logger = getLogger("components/themes/default/Header");

export default {
  name: "Header",
  components: {
    HeaderMenu
  },
  data() {
    return {
      s: "",
      searchLink: "/s-dark/"
    };
  },
  watch: {
    s() {
      this.searchLink = "/s-dark/" + this.s;
    }
  },
  methods: {
    doSearch() {
      logger.info("this.searchLink=>" + this.searchLink);
      this.$router.push({ path: this.searchLink });
      // this.$router.go(this.searchLink);
    }
  }
};
</script>

<style lang="scss" scoped>
/**
* 图片居中
*/
.img-wrap {
  background: #212121;
  text-align: left;
  max-height: 45px;
  margin-top: 10px;
}
.img-wrap:hover {
  background: transparent;
}
.img-wrap .img-align-middle:before {
  content: " ";
  display: inline-block;
  vertical-align: middle;
  height: 100%;
}
.logo {
  max-height: 45px;
}
/* 菜单 */
.el-menu--horizontal > .el-menu-item {
  height: 60px !important;
}
.el-menu--horizontal > .el-submenu .el-submenu__title {
  height: 60px !important;
}
/* 搜索 */
.search {
  margin: 0 10px;
}
</style>
