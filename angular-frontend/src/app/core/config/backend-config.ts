export const BackendConfig = {
  springApiUrl: getSpringApiUrl()
};

function getSpringApiUrl(): string {
  return window.location.hostname === 'localhost'
    ? 'http://localhost:8080'
    : 'http://bellamy-spring.us-east-2.elasticbeanstalk.com';
}
