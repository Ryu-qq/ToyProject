const BACKEND_DOMAINPORT = process.env.VUE_APP_API_URL;
const FRONTEND_DOMAINPORT = process.env.VUE_APP_API_URL;

const REDIRECT_URI = `${FRONTEND_DOMAINPORT}/oauth/redirect`;

export default {
	getSocialLoginUrl(socialType) {
		return `${BACKEND_DOMAINPORT}/oauth2/authorization/${socialType}?redirect_uri=${REDIRECT_URI}`;
	},
	getSocialImage(socialType) {
		switch (socialType) {
			case 'google':
				return '@/assets/btn_google_signin_light_pressed_web@2x.png';
		}
	},
	clickOutside(exceptionId = '#') {
		return {
			bind: function (el, binding, vnode) {
				el.clickOutsideEvent = function (e) {
					if (
						!(el === e.target || el.contains(e.target)) &&
						e.target.id !== exceptionId
					) {
						vnode.context[binding.expression](e);
					}
				};
				document.body.addEventListener('click', el.clickOutsideEvent);
			},
			unbind: function (el) {
				document.body.removeEventListener('click', el.clickOutsideEvent);
			},
		};
	},
};
