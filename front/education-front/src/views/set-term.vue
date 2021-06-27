<template>
    <div class="top">
        <el-select v-model="value" placeholder="请选择学期">
            <el-option
                    v-for="item in options"
                    :key="item.termId"
                    :label="item.termName"
                    :value="item.termId">
            </el-option>
        </el-select>
        <el-button type="primary" @click="set()">设置</el-button>
    </div>
</template>

<script>
    export default {
        name: "set-term",
        data() {
            return {
                options: [],
                value: 0,
            }
        },
        methods: {
            getTermList() {
                const _this = this;
                this.$axios.get("/term/getAll?notAll=1",
                    {
                        headers: {
                            "Authorization": _this.$store.getters.getToken
                        }
                    }
                ).then(response => {
                    var data = response.data.data;
                    _this.options = data.list;
                    _this.value = data.this;
                })
            },
            set() {
                this.$axios.get("/term/set?termId=" + this.value,
                    {
                        headers: {
                            "Authorization": this.$store.getters.getToken
                        }
                    }
                ).then(response=>{
                    this.$message({
                        message: response.data.message,
                        type: "success"
                    })
                })
            }
        },
        created() {
            this.getTermList()
        }
    }
</script>

<style scoped>

</style>