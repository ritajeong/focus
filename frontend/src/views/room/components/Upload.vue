<template>
  <div class="example-drag">
    <div v-show="$refs.upload && $refs.upload.dropActive" class="drop-active">
      <h3>Drop files to upload</h3>
    </div>
    <template v-if="files.length">
      <v-data-table
        dense
        :headers="headers"
        :items="files"
        item-key="name"
        class="elevation-1"
        hide-default-footer
      >
      </v-data-table>
      <v-btn
        class="ma-2"
        style="padding: 0px"
        color="info"
        @click="uploadStart()"
      >
        <v-icon style="margin-right: 5px">arrow_upward</v-icon>UpLoad Start
      </v-btn>
    </template>
    <template v-else>
      <!-- <input
        v-on:change="fileSelect()"
        type="file"
        ref="img"
        id="File"
        multiple="multiple"
        drop="true"
        drop-directory="true"
      /> -->

      <FileUpload
        class="btn btn-primary"
        :multiple="true"
        :drop="true"
        :drop-directory="true"
        type="img"
        v-model="files"
        ref="img"
        style="display: none"
      >
      </FileUpload>

      <v-row>
        <v-col cols="12" sm="12" md="12">
          <div class="text-center p-5">
            <h4>Drop files anywhere to upload<br />or</h4>
            <v-btn class="ma-2" style="padding: 0px" color="info">
              <label for="file" style="padding: 0px 8px"
                ><v-icon style="margin-right: 5px">add_circle</v-icon>Select
                File</label
              >
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </template>
  </div>
</template>
<script>
import { downloadFile } from '@/api/file.js';
import FileUpload from 'vue-upload-component';

export default {
  components: {
    FileUpload,
  },
  data: () => ({
    files: [],
    headers: [
      { text: 'name', value: 'name' },
      { text: 'size', value: 'size' },
    ],
    userid: '',
    roomid: '',
  }),
  methods: {
    uploadStart() {
      const formData = new FormData();
      this.userid = this.$store.state.users.login.userid;
      this.roomid = this.$store.state.rooms.room.room_id;
      formData.append('user_id', this.userid);
      formData.append('room_id', this.roomid);
      formData.append('size', '0');

      for (let i = 0; i < this.files.length; i++) {
        formData.append('files', this.files[i].file);
      }
      downloadFile(formData)
        .then(({ status }) => {
          if (status != 200) {
            return;
          } else {
            this.$alertify.success('파일이 업로드 됐습니다.');
            this.$emit('change');
          }
        })
        .catch(() => {
          this.$alertify.error('error! catch');
        });
    },
  },
};
</script>
<style>
.example-drag .drop-active {
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  position: fixed;
  z-index: 9999;
  opacity: 0.6;
  text-align: center;
  width: 100%;
  background: #000;
}
.example-drag .drop-active h3 {
  margin: -0.5em 0 0;
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
  font-size: 40px;
  color: #fff;
  padding: 0;
}
</style>
