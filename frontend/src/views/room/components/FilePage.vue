<template>
  <v-app id="inspire">
    <v-container fluid class="pa-0">
      <v-row align="center">
        <v-col cols="12" sm="12" md="12">
          <div class="text-center">
            <div class="my-2">
              <v-btn @click="openDialog()" large color="blue" dark>
                <v-icon class="mr-2">cloud_upload</v-icon>
                Upload
              </v-btn>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <v-dialog v-model="dialog" persistent max-width="900px">
      <v-card>
        <v-card-title>
          <template>
            <v-icon style="margin-right: 10px" large color="#41B883"
              >cloud_upload</v-icon
            >
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
              <Upload></Upload>
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-app>
</template>

<script>
import Upload from './Upload.vue';

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
    },
  },
};
</script>
