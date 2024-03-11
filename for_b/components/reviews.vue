<template>
    <div class="text-center py-28 px-10">
        <UBadge color="primary" variant="solid">미용실에 신뢰받는</UBadge>
        <div class="text-3xl font-bold pt-3 pb-16 text-center">고객 리뷰</div>

        <div class="flex flex-col md:flex-row gap-10">
            <div class="flex-1 flex flex-col items-center gap-4" v-for="review in reviews">
                <UIcon class="text-7xl" name="i-heroicons-user" />
                <div class="font-semibold">
                    {{ review.label }}
                </div>
                <div class="text-slate-400">{{ review.content }}</div>
            </div>

        </div>
    </div>
</template>

<script setup>

const reviews = [
    { label: "시간을 아끼는 효율적인 예약 관리", content: "저희 미용실은 이 플랫폼을 사용하여 예약을 간단하게 관리하고 있습니다. 예약 일정을 확인하고 수정하는 것이 너무 쉬워서 운영에 큰 도움이 됩니다. 고객들도 쉽게 예약을 할 수 있어 만족도가 높아졌습니다." },
    { label: "고객 만족도 급상승!", content: "예약 플랫폼을 사용한 후에는 고객들의 만족도가 급격히 상승했습니다. 예약 시스템이 신속하고 효율적이어서 고객들이 편리하게 예약할 수 있게 되었고, 자동화된 알림 기능 덕분에 예약 시간을 놓치지 않게 되었습니다. 이러한 서비스로 우리의 고객들은 더 이상 다른 곳을 찾지 않고 저희 미용실을 자주 이용하게 되었습니다." },
    { label: "비즈니스 성장의 열쇠!", content: "저희 비즈니스는 이 플랫폼을 도입한 후에 급속한 성장을 이루어냈습니다. 예약 관리와 고객 만족도가 향상되어 많은 고객들이 저희 미용실을 선택하게 되었고, 이에 따라 매출도 상당히 증가했습니다. 또한 직원들의 업무 효율성도 향상되어 업무환경이 개선되었습니다. 이 플랫폼은 우리 비즈니스의 성장을 위한 필수 도구가 되었습니다." }
]
</script>