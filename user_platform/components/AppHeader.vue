<template>
    <div :style="scrolled ? 'background-color:#1d1d1d; color:white' : 'background-color:white'"
        class="sticky top-0 z-10 bg-white flex flex-row justify-between items-center md:px-6 md:py-3 px-2 py-3">
        <div class="flex flex-row gap-8 items-center">
            <nuxt-link to="/" class="flex flex-row gap-1 items-center">
                <UIcon class="text-xl rotate-45" name="i-heroicons-scissors" />
                <div class="text-xl font-semibold">Salon Search</div>
            </nuxt-link>

            <div class="flex flex-row gap-3 max-sm:hidden">
                <div v-for="nav in tabs">
                    <nuxt-link :to="nav.to">{{ nav.label }}</nuxt-link>
                </div>
            </div>
        </div>


        <div class="max-sm:hidden float-end">
            <UButton @click="loginDialog = true" :color="scrolled ? 'white' : 'black'" variant="solid" class="px-4">로그인
            </UButton>
        </div>

        <UButton @click="loginDialog = true" :color="scrolled ? 'white' : 'black'" class="sm:hidden"
            icon="i-heroicons-ellipsis-vertical" :padded="false" variant="link" />
    </div>

    <UModal v-model="loginDialog">
        <UForm :state="state" class="space-y-4 px-5 py-2 max-sm:pb-52" @submit="onSubmit">
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

                <UButton style="background-image: url('kakao_login_medium_wide.png'); background-position: center center; background-repeat: no-repeat; height: 44px;" size="xl" block color="yellow" @click="handleKakaoLogin"
                    >
                </UButton>
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
    { label: '홈', to: '/' },
    { label: '내 주변', to: '/' },
    { label: '예약', to: '/' },
    { label: '마이 페이지', to: '/' },
    { label: '샵', to: '/' },
];

const route = useRoute()
const $router = useRouter();

const state = reactive({
    email: undefined,
    password: undefined
})

async function onSubmit(event) {
    console.log(event.data)
}

async function handleKakaoLogin() {
    // Kakao.init(runtimeConfig.public.kakaoJsApiKey);
    // console.log(Kakao.isInitialized());

    // Kakao.Auth.authorize({
    //     redirectUri: `${window.location.origin}/kakao-callback`,
    //     prompt: 'login',
    // });
    const loginResponse = await useFetch('http://43.201.246.72:8080/oauth2/authorization/kakao')
    console.log(loginResponse)
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
