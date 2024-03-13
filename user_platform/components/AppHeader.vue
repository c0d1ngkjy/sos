<template>
    <div :style="scrolled ? 'background-color:#1d1d1d; color:white' : 'background-color:white'"
        class="sticky top-0 bg-white flex flex-row justify-between items-center md:px-6 md:py-3 px-2 py-3">
        <nuxt-link to="/" class="flex flex-row gap-1 items-center">
            <UIcon class="text-xl rotate-45" name="i-heroicons-scissors" />
            <div class="text-xl font-semibold">Salon Search</div>
        </nuxt-link>

        <div class="flex flex-row gap-3 max-sm:hidden">
            <div v-for="nav in tabs">
                <nuxt-link :class="scrolled ? 'text-gray-400 hover:text-white' : 'text-gray-500 hover:text-gray-900'" :to="nav.path">{{ nav.name }}</nuxt-link>
            </div>
        </div>

        <div class="max-sm:hidden">
            <UButton @click="loginDialog = true" :color="scrolled? 'white' : 'black'" variant="solid" class="px-4">로그인</UButton>
        </div>

        <UButton @click="loginDialog = true" class="sm:hidden" icon="i-heroicons-ellipsis-vertical" :padded="false"
            color="black" variant="link" />
    </div>

    <UModal v-model="loginDialog">
        <UForm :state="state" class="space-y-4 px-5 py-2" @submit="onSubmit">
            <div class="text-lg font-semibold py-4">Login</div>
            <UFormGroup label="이메일" name="email">
                <UInput size="lg" v-model="state.email" />
            </UFormGroup>

            <UFormGroup label="비밀번호" name="password">
                <UInput size="lg" v-model="state.password" type="password" />
            </UFormGroup>

            <div class="flex flex-col gap-2">
                <UButton block color="black" size="xl" type="submit">
                    로그인
                </UButton>

                <UButton block @click="handleKakaoLogin" color="yellow" size="xl">카카오 로그인</UButton>
            </div>

            <div class="flex flex-row justify-evenly pt-1 pb-3 text-sm text-primary-300">
                <div class="hover:underline hover:text-primary-800 cursor-pointer">비밀번호를 잊어버리셨나요?</div>
                <nuxt-link @click="loginDialog = false" to="/register"
                    class="hover:underline hover:text-primary-800 cursor-pointer">회원가입하기</nuxt-link>
            </div>
        </UForm>
    </UModal>
</template>

<script setup>
const runtimeConfig = useRuntimeConfig();

const loginDialog = ref(false);
const tabs = [
    { name: '홈', path: '/' },
    { name: '미용실 찾기', path: '/' },
    { name: '문의하기', path: '/' },
];
const state = reactive({
    email: undefined,
    password: undefined
})

async function onSubmit(event) {
    console.log(event.data)
}

function handleKakaoLogin() {
    Kakao.init(runtimeConfig.public.kakaoJsApiKey);
    console.log(Kakao.isInitialized());

    Kakao.Auth.authorize({
        redirectUri: `${window.location.origin}/kakao-callback`,
        prompt: 'login',
    });
}

const scrolled = ref(false);
const handleScroll = () => {
    scrolled.value = window.scrollY > 10;
};
onMounted(() => {
    window.addEventListener('scroll', handleScroll);
})
onBeforeUnmount(() => {
    window.removeEventListener('scroll', handleScroll);
});
</script>