<template>
  <v-app id="inspire">
    <!-- Page.vue - Dialog를 열기위한 버튼 영역 -->
    <v-container fluid class="pa-0">
      <v-row align="center">
        <v-col cols="12" sm="12" md="12">
          <div class="text-center">
            <div class="my-2">
              <v-tooltip top content-class="className">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    @click="openDialog()"
                    class="bg-gradient-dark white--text"
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon class="mr-2">cloud_upload</v-icon>
                    File Upload
                  </v-btn>
                </template>
                <span>발표자료를 업로드 해주세요.</span>
              </v-tooltip>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <!-- Page.vue - Dialog 영역 -->
    <v-dialog v-model="dialog" persistent max-width="900px">
      <v-card>
        <v-card-title>
          <template>
            <v-icon style="margin-right: 10px" large color="#41B883">
              cloud_upload
            </v-icon>
            <span class="headline" large>파일 업로드</span>
          </template>
          <v-spacer></v-spacer>
          <v-btn icon @click="closeDialog()">
            <v-icon>clear</v-icon>
          </v-btn>
        </v-card-title>
        <v-card-text>
          <v-row>
            <v-col
              cols="12"
              sm="12"
              md="12"
              style="
                position: relative;
                border: 1px solid #41b883;
                border-style: dashed;
              "
            >
              <Upload @change="change"></Upload>
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-app>
</template>

<style>
.v-application--wrap {
  min-height: 10vh !important;
}
.className {
  transform: translate(0, -100%);
}
</style>

<script>
import Upload from './Upload.vue';
//import {showRoomdetail} from '@/api/file.js';
export default {
  components: {
    Upload,
  },
  data: () => ({
    dialog: false,
  }),
  methods: {
    openDialog() {
      this.dialog = true;
    },
    closeDialog() {
      this.dialog = false;
      this.$router.go();
    },
    change() {
      this.dialog = false;
      window.location.reload();
    },
  },
};
</script>
